package com.example.minstrom.utils


import android.util.Log
import com.example.minstrom.ui.viewModel.ViewModel
import com.google.firebase.messaging.FirebaseMessaging

// Function to fetch Firebase Token and add it to the ViewModel
fun fetchFirebaseToken(viewModel: ViewModel) {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w("FCM", "Fetching FCM registration token failed", task.exception)
            return@addOnCompleteListener
        }
        val token = task.result
        viewModel.tokens.add(token)  // Add token to the ViewModel
        Log.d("FCM", "Device token: $token")
    }
}
