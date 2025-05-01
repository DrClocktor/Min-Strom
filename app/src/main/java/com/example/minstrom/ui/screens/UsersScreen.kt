package com.example.minstrom.ui.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R
import com.example.minstrom.ui.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(navController: NavController, viewModel: UserViewModel) {
    var isEditing by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("Familienavn") }
    val users by viewModel.userList.collectAsState()

    Column {
        users.forEach { user ->
            Text(
                text = user,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isEditing) {
                            OutlinedTextField(
                                value = userName,
                                onValueChange = { userName = it },
                                singleLine = true,
                                modifier = Modifier
                                    .height(48.dp)
                                    .weight(1f)
                            )
                            TextButton(onClick = { isEditing = false }) {
                                Text("Gem")
                            }
                        } else {
                            Spacer(modifier = Modifier.width(17.dp))
                            Text(
                                text = userName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            IconButton(onClick = { isEditing = true }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Rediger",
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("menu") {
                            popUpTo("users") { inclusive = true }
                        }
                    }) {
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
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.family_illustration),
                contentDescription = "Familiebillede",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E6E6))
            ) {
                // Administrator (fast)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_female_admin),
                        contentDescription = "Admin ikon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Administrator (dig)", fontSize = 18.sp, modifier = Modifier.weight(1f))
                    IconButton(onClick = { navController.navigate("add_user") }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Flere indstillinger")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Dynamisk liste over tilføjede brugere
                users.forEach { userName ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_users),
                                contentDescription = "Bruger ikon",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = userName,
                                fontSize = 18.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { navController.navigate("add_user") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_users),
                    contentDescription = "Bruger ikon",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Familie", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}}
/*
@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    UsersScreen(navController = rememberNavController())
}
*/
