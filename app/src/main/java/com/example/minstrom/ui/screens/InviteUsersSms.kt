package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import android.telephony.SmsManager
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteUsersSms(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    val isPhoneNumberValid = isValidPhoneNumber(phoneNumber)
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Indtast nummer", fontSize = 25.sp, fontWeight = FontWeight.Bold)
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
            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Indtast telefonnummeret på brugeren du ønsker at tilføje til planlægningen",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(35.dp))

            Image(
                painter = painterResource(id = R.drawable.invite), //  billede her
                contentDescription = "Telefon illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(35.dp))

            // 👇 Telefonnummer TextField
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Telefonnummer") },
                placeholder = { Text("12 34 56 78") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.flag_denmark), // 👈 Dit flag billede
                        contentDescription = "Flag",
                        modifier = Modifier.size(30.dp),
                    )
                },
                trailingIcon = {
                    if (isPhoneNumberValid) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_verified), // dit grønne check ikon
                            contentDescription = "Verified",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                },
                    keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
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

                    focusedIndicatorColor = Color.Transparent, // 👈 No line when focused
                    unfocusedIndicatorColor = Color.Transparent // 👈 No line when unfocused
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Blå "Fortsæt" knap
            Button(
                onClick = {
                    if (isPhoneNumberValid) {
                        val message = "Hej! Vil du være med i Min Strøm appen? Download her:https://apps.apple.com/dk/app/min-str%C3%B8m/id1615436711"
                        inviteUserSMS(context, phoneNumber, message)
                        showDialog = true
                    }
                },
                modifier = Modifier
                    .width(220.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A7EFD)
                )
            ) {
                Text(text = "Fortsæt", fontSize = 18.sp)
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
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Nu kan du sende invitationen!")
                    },
                    text = {
                        Text(text = "Når modtageren får et link på SMS og  vil de blive tilføjet til planlægningsfunktionen i Min Strøm⚡.")
                    }
                )
            }
        }
    }
}

fun inviteUserSMS(context: Context, phoneNumber: String, message: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("smsto:$phoneNumber")
        putExtra("sms_body", message)
    }
    context.startActivity(intent)
}


fun isValidPhoneNumber(number: String): Boolean {
    val digitsOnly = number.filter { it.isDigit() }
    return digitsOnly.length >= 8 // eller hvad du vil kræve
}

@Preview(showBackground = true)
@Composable
fun InviteUsersSmsPreview() {
}


