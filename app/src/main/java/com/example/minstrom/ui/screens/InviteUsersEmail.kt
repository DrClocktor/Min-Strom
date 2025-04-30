package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteUsersEmail(navController: NavController) {
    var email by remember { mutableStateOf("") }
    val isEmailValid = isValidEmail(email)
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Indtast e-mail", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Tilbage"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("menu") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu",
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
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Indtast e-mailadressen på brugeren du ønsker at invitere til planlægningen",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(35.dp))

            Image(
                painter = painterResource(id = R.drawable.email), // Brug samme billede
                contentDescription = "Email illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(35.dp))

            // 👇 Email TextField
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                placeholder = { Text("example@email.com") },
                trailingIcon = {
                    if (isEmailValid) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_verified), // Dit grønne check ikon
                            contentDescription = "Verified",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF2F2F2),
                    unfocusedContainerColor = Color(0xFFF2F2F2),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Blå "Fortsæt" knap
            Button(
                onClick = {
                    showDialog = true
                },
                enabled = isEmailValid, // Kun aktiv når email er valid!
                modifier = Modifier
                    .width(220.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A7EFD),
                    disabledContainerColor = Color(0xFF0A7EFD) //
                )
            ) {
                Text(text = "Fortsæt", fontSize = 18.sp, color = Color.White)
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A7EFD)
                            )
                        ) {
                            Text("OK", color = Color.White)
                        }
                    },
                    title = {
                        Text(text = "Invitation sendt!")
                    },
                    text = {
                        Text(text = "Den inviterede vil modtage et link på e-mail og blive tilføjet til planlægningsfunktionen i Min Strøm⚡.")
                    }
                )
            }
        }
    }
}

// Validerer om emailen er korrekt
fun isValidEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".") && email.length > 7
}

@Preview(showBackground = true)
@Composable
fun InviteUsersEmailPreview() {
    InviteUsersEmail(navController = rememberNavController())
}
