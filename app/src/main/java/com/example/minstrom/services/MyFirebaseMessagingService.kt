package com.example.minstrom.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.minstrom.MainActivity
import com.example.minstrom.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "com.example.minstromn"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        println("Message received from cloud")
        // Check if the notification payload exists
        remoteMessage.notification?.let {
            generateNotification(it.title ?: "No Title", it.body ?: "No Message")
        }
    }

    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews("com.example.minstrom", R.layout.notification)
        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.message, message)
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.skaermbillede)
        return remoteView
    }

    private fun generateNotification(title: String, message: String) {
        println("Generating notification")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.skaermbillede)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            // .setContent(getRemoteView(title, message))
            .setContentTitle(title)
            .setContentText(message)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationId = (System.currentTimeMillis() / 1000).toInt()
        notificationManager.notify(notificationId, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}