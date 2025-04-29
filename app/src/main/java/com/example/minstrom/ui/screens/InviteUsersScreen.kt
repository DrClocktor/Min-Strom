package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteUsersScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Inviter brugere",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("menu") {
                            popUpTo("menu") { inclusive = true }
                        }
                    }) {
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Inviter nye eller\neksisterende brugere til din\nplanlægning",
                fontSize = 19.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.family),
                contentDescription = "Invitation illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(15.dp))

            InviteOption(
                iconRes = R.drawable.ic_facebook,
                title = "Facebook",
                subtitle = "Inviter brugere fra Facebook",
                onClick = { /* Handling for Facebook */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            InviteOption(
                iconRes = R.drawable.ic_email,
                title = "Email",
                subtitle = "Inviter brugere via Email",
                onClick = { navController.navigate("invite_email") // til InviteUsersEmail
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            InviteOption(
                iconRes = R.drawable.ic_contacts,
                title = "Kontakter",
                subtitle = "Inviter brugere via SMS",
                onClick = {
                    navController.navigate("invite_sms") // Navigerer til InviteUsersSms
                }
            )
        }
    }
}

@Composable
fun InviteOption(iconRes: Int, title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Pil frem",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InviteUsersScreenPreview() {
    InviteUsersScreen(navController = rememberNavController())
}
