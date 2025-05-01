package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanningScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Planlægning", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {navController.navigate("menu")}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = { navController.navigate("add_plan")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0A7EFD),
                        contentColor = MaterialTheme.colorScheme.primary ),

                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        focusedElevation = 0.dp,
                        hoveredElevation = 0.dp
                    )
                ) {
                    Text("Tilføj plan", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Tabs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                TabButton("I dag", selected = selectedTab == 0) { selectedTab = 0 }
                TabButton("Uge", selected = selectedTab == 1) { selectedTab = 1 }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Graf over priser
            Text(
                text = "De næste 7 dages priser",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(25.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(Color.White, shape = RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.electricity_prices_chart),
                    contentDescription = "Prisgraf",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            // Ugeplan
            Text("Ugeplan", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))

            PlanCard(
                title = "Vaskemaskine",
                days = listOf("\uD83D\uDFE2 Man", "\uD83D\uDFE1 Ons", "\uD83D\uDD34 Søn"),
                time = "16:00 - 20:30",
                iconRes = R.drawable.ic_washing_machine
            )

            Spacer(modifier = Modifier.height(12.dp))

            PlanCard(
                title = "Opvaskemaskine",
                days = listOf("\uD83D\uDFE2 Man", "\uD83D\uDFE1 Ons", "\uD83D\uDD34 Søn"),
                time = "16:00 - 20:30",
                iconRes = R.drawable.ic_dishwasher
            )
            Spacer(modifier = Modifier.height(12.dp))

            PlanCard(
                title = "El-Bil",
                days = listOf("\uD83D\uDFE2 Man", "\uD83D\uDFE1 Tirs", "\uD83D\uDD35 Fre", "\uD83D\uDD34 Søn"),
                time = "16:00 - 20:30",
                iconRes = R.drawable.ic_car
            )
        }
    }
}

@Composable
fun TabButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(bottom = 8.dp),
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        fontSize = 16.sp,
        color = if (selected) Color.Black else Color.Gray,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PlanCard(title: String, days: List<String>, time: String, iconRes: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6))
    ) {
        Row(modifier = Modifier.padding(19.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(53.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    days.forEach {
                        Text(text = it, modifier = Modifier.padding(end = 8.dp, bottom = 8.dp, top =8.dp ), fontSize = 14.sp)
                    }
                }
                Text("kl. $time ⏰", fontSize = 14.sp)
            }
            IconButton(onClick = { /* mere menu */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Mere")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanningScreenPreview() {
}