package com.example.minstrom


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.data.model.ViewModel
import com.example.minstrom.ui.screens.*
import com.example.minstrom.ui.theme.MinStromTheme
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
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
                            // 👇 Tilføj dine nye skærme
                            composable("planning") {
                                PlanningScreen(navController)
                            }
                            composable("menu") {
                                MenuScreen(navController)
                            }
                            composable("add_plan") {
                                AddPlanScreen(navController)
                            }
                        }
                    }
                }

                // Hent og print FCM-token
                FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("FCM", "Hentning af FCM-token fejlede", task.exception)
                        return@addOnCompleteListener
                    }
                    val token = task.result
                    viewModel.tokens.add(token)
                    println("Device token: $token")
                }
            }
        }
    }
}

@Composable
fun NotificationTestScreen(viewModel: ViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Greeting(name = "Android")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.sendNotification(
                context,
                title = "Test",
                message = "testing..."
            )
        }) {
            Text("Send notification")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinStromTheme {
        Greeting("Android")
    }
}
