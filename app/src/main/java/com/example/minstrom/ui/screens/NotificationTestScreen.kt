package com.example.minstrom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.minstrom.ui.viewModel.ViewModel

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
