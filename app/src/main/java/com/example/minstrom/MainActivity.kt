package com.example.minstrom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.navigation.AppNavigation
import com.example.minstrom.ui.theme.MinStromTheme
import com.example.minstrom.ui.viewModel.UserViewModel
import com.example.minstrom.ui.viewModel.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {

    private val notificationViewModel: ViewModel by viewModels() // fx til notifikationer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinStromTheme {
                val navController = rememberNavController()
                val userViewModel: UserViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppNavigation(
                            navController = navController,
                            viewModel = notificationViewModel,
                            userViewModel = userViewModel
                        )
                    }
                }
            }
        }

        fetchFCMToken()
    }

    private fun fetchFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            notificationViewModel.tokens.add(token)
            Log.d("FCM", "Device token: $token")
        }
    }
}