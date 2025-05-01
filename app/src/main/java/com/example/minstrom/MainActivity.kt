package com.example.minstrom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.navigation.AppNavigation
import com.example.minstrom.data.model.ViewModel
import com.example.minstrom.ui.screens.*
import com.example.minstrom.ui.test.UsersScreen
import com.example.minstrom.ui.theme.MinStromTheme
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private val viewModel: com.example.minstrom.ui.viewModel.ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinStromTheme {
                val navController = rememberNavController()
                val viewModel: ViewModel by viewModels()
                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "planning",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("planning") {
                                PlanningScreen(navController)
                            }
                            composable("add_plan") {
                                AddPlanScreen(navController)
                            }
                            composable("menu") {
                                MenuScreen(navController)
                            }
                            composable("users") {
                                UsersScreen(navController = navController)
                            }
                            composable("add_user") {
                                AddUsersScreen(navController = navController)
                            }
                            composable("invite_users") {
                                InviteUsersScreen(navController)
                            }
                            composable("invite_sms") {
                                InviteUsersSms(navController)
                            }
                            composable("invite_email") {
                                InviteUsersEmail(navController)
                            }
                            composable("notification_test") {
                                NotificationTestScreen(viewModel)
                            }

                        }
                    }
                }
            MaterialTheme {
                MainContent(viewModel)
            }
        }

        // Fetch and print the FCM token
        fetchFCMToken()
    }

    private fun fetchFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            viewModel.tokens.add(token)
            Log.d("FCM", "Device token: $token")
        }
    }
}

@Composable
fun MainContent(viewModel: com.example.minstrom.ui.viewModel.ViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            AppNavigation(navController, viewModel)
        }
    }
}
