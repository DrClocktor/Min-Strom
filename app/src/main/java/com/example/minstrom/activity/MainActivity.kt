package com.example.minstrom.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.data.model.ViewModel
import com.example.minstrom.ui.theme.MinStromTheme
import com.example.minstrom.navigation.AppNavigation // Import the AppNavigation
import com.example.minstrom.ui.screens.NotificationTestScreen // Import the NotificationTestScreen
import com.example.minstrom.utils.fetchFirebaseToken // Import the Firebase token fetching function
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
                        AppNavigation(navController, viewModel)
                    }
                }

                // Fetch and print the FCM token
                fetchFirebaseToken(viewModel)

            }
        }
    }
}
//