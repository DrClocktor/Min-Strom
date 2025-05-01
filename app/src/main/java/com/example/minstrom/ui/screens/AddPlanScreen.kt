package com.example.minstrom.ui.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minstrom.R
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlanScreen(navController: NavController) {
    val context = LocalContext.current
    var note by remember { mutableStateOf(TextFieldValue("")) }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Planlægning", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Tilbage")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("menu") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Vaskemaskine", fontSize = 16.sp)

            Image(
                painter = painterResource(id = R.drawable.electricity_prices_chart),
                contentDescription = "Graf",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            PlanActionButton(
                icon = R.drawable.ic_time,
                label = "Vælg tid",
                onClick = { showTimePicker = true }
            )
            PlanActionButton(
                icon = R.drawable.ic_notifications,
                label = "Notifikationer",
                onClick = { showTimePicker = true }
            )
            PlanActionButton(
                icon = R.drawable.ic_users,
                label = "Tilføj brugere",
                onClick = { navController.navigate("add_user") }
            )
            PlanActionButton(
                icon = R.drawable.ic_calender,
                label = "Kalender",
                onClick = { showTimePicker = true }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Tilføj ny note") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                shape = RoundedCornerShape(10.dp),
                supportingText = { Text("${note.text.length}/30") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Håndtere færdig */ },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Færdig", fontSize = 18.sp, color = Color.White)
            }
        }

        if (showTimePicker) {
            AndroidTimePicker(
                context = context,
                onTimeSelected = { hour, minute ->
                    selectedTime = String.format("%02d:%02d", hour, minute)
                    showTimePicker = false
                },
                onDismiss = { showTimePicker = false }
            )
        }
    }
}

@Composable
fun PlanActionButton(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(vertical = 6.dp)
            .background(Color(0xFFEFEFEF), RoundedCornerShape(15.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(44.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, fontSize = 16.sp)
    }
}

@Composable
fun AndroidTimePicker(
    context: android.content.Context,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = java.util.Calendar.getInstance()
    val hour = currentTime.get(java.util.Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(java.util.Calendar.MINUTE)

    DisposableEffect(Unit) {
        val dialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                onTimeSelected(selectedHour, selectedMinute)
            },
            hour,
            minute,
            true
        )
        dialog.setOnDismissListener { onDismiss() }
        dialog.show()

        onDispose { dialog.dismiss() }
    }
}





@Preview(showBackground = true)
@Composable
fun AddPlanScreenPreview() {
    AddPlanScreen(navController = rememberNavController())
}
