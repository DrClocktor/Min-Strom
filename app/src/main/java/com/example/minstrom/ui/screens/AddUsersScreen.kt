package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUsersScreen(navController: NavController) {
    var userName by remember { mutableStateOf("Navn") }
    var isEditing by remember { mutableStateOf(false) }
    var accessLevel by remember { mutableStateOf("Kan tilføje plan og fjerne") }
    val accessOptions = listOf(
        "Kan tilføje plan og fjerne",
        "Kan kun se planer",
        "Administrator"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tilføj brugere", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Tilbage")
                    }
                },
                actions = {
                    IconButton(onClick = {navController.navigate("menu")}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
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
            Spacer(modifier = Modifier.height(10.dp))

            // Navn med rediger-knap
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isEditing) {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { isEditing = false }) {
                        Text("Gem")
                    }
                } else {
                    Text(userName, fontSize = 18.sp)
                    IconButton(onClick = { isEditing = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Rediger navn")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Billede
            Image(
                painter = painterResource(id = R.drawable.ic_profile_placeholder),
                contentDescription = "Profilbillede",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .clickable { /* TODO: upload billede */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Dropdown for adgang
            Text("Adgang:", modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(4.dp))
            var expanded by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(10.dp))
                    .clickable { expanded = true }
                    .padding(12.dp)
            ) {
                Text(accessLevel)
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    accessOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                accessLevel = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("invite_users")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Inviter bruger", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun AddUsersScreenPreview() {
    AddUsersScreen(navController = rememberNavController())
}
*/