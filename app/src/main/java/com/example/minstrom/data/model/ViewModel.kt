package com.example.minstrom.data.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.ByteArrayContent
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpHeaders
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpResponse
import com.google.api.client.json.gson.GsonFactory
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class ViewModel: ViewModel() {

    val tokens: MutableList<String> = mutableListOf()

    fun sendNotification(
        context: Context,
        title: String,
        message: String,
    ){
        viewModelScope.launch {
            sendFCMNotification(
                context,
                title,
                message,
            )
        }
    }

    private suspend fun sendFCMNotification(
        context: Context,
        title: String,
        message: String,
    ) = withContext(Dispatchers.IO) {
        println("sendFCMNotification trying to get credentials")
        val credentials = getServiceAccountAccessToken(context)

        if (credentials == null) {
            println("Failed to get service account credentials")
            return@withContext
        }

        // Initialize the transport and request factory
        val transport = GoogleNetHttpTransport.newTrustedTransport()
        val jsonFactory: GsonFactory = GsonFactory.getDefaultInstance()

        // Create the HTTP request factory with OAuth 2.0 credentials
        val requestFactory = transport.createRequestFactory(HttpCredentialsAdapter(credentials))

        // Build the URL for FCM HTTP v1 API endpoint
        val url = "https://fcm.googleapis.com/v1/projects/min-strom-79afb/messages:send"

        // Create the message data class
        data class Notification(val title: String, val body: String)
        data class Message(val token: String, val notification: Notification)

        // Create the message object
        val notification = Notification(title, message)
        var i = 0
        for (token in tokens) {
            i += 1
            println("Sending notification to token $i of ${tokens.size}: $token")

            val messageObject = Message(token, notification)

            // Convert the message object to JSON using Gson
            val gson = Gson()
            val messageJson = gson.toJson(messageObject)

            // Create the HTTP request
            val request: HttpRequest = requestFactory.buildPostRequest(
                GenericUrl(url),
                ByteArrayContent.fromString("application/json", "{\"message\": $messageJson}"),
            )

            // Set the Authorization header with the OAuth token
            request.headers = HttpHeaders()
            request.headers.authorization = "Bearer ${credentials.accessToken}"

            // Send the request
            val response: HttpResponse = request.execute()

            // Check response
            if (response.statusCode == 200) {
                println("Notification sent successfully!")
            } else {
                println("Failed to send notification: ${response.statusCode}")
            }
        }
    }

    private suspend fun getServiceAccountAccessToken(context: Context): GoogleCredentials? = withContext(
        Dispatchers.IO) {
        println("Inside getServiceAccountAccessToken")
        try {
            // Access the file from the assets directory
            val inputStream: InputStream = context.assets.open("min-strom-79afb-firebase-adminsdk-fbsvc-3a892562f5.json")
            val credentials = ServiceAccountCredentials
                .fromStream(inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))

            println("Credentials: $credentials")

            // Refresh the access token on a background thread
            // credentials.refreshAccessToken() // Removed this line

            return@withContext credentials
        } catch (e: Exception) {
            println("Error loading service account credentials: ${e.message}")
            e.printStackTrace()
            println("Exception type: ${e::class.java.name}") // Print the type of exception
            return@withContext null
        }
    }
}