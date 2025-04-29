package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Menu", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("planning") {
                            popUpTo("planning") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Top grid (2x2)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuItem(
                        iconRes = R.drawable.ic_devices,
                        label = "Mine enheder",
                        onClick = { /* TODO: Naviger til enheder senere */ }
                    )
                    MenuItem(
                        iconRes = R.drawable.ic_users,
                        label = "Brugere",
                        onClick = {
                            navController.navigate("invite_users") // til InviteUsersScreen
                        }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuItem(
                        iconRes = R.drawable.ic_add,
                        label = "Tilføj enhed",
                        onClick = { /* TODO: Tilføj navigation  */ }
                    )
                    MenuItem(
                        iconRes = R.drawable.ic_minstroom,
                        label = "Læs mere",
                        backgroundColor = Color(0xFF0A7EFD),
                        iconTint = Color.White,
                        onClick = { /* TODO: Læs mere screen  */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Divider()
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_help),
                        contentDescription = "Hjælp",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text("Hjælp", fontSize = 16.sp)
                }
            )
            Divider()
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notifications),
                        contentDescription = "Notifikationer",
                        modifier = Modifier.size(35.dp)
                    )
                },
                headlineContent = {
                    Text("Notifikationer", fontSize = 16.sp)
                }
            )
            Divider()
        }
    }
}

@Composable
fun MenuItem(
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color(0xFFF2F2F2),
    iconTint: Color = Color.Unspecified
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(120.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(backgroundColor, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(50.dp)
                 )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}
