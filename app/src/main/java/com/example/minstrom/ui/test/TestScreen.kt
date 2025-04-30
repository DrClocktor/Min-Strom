package com.example.minstrom.ui.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minstrom.ui.viewModel.TestScreenViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

@Composable
fun CreateFamily() {
    var familyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Opret ny familie", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = familyName,
            onValueChange = { familyName = it },
            label = { Text("Familienavn") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Administrator e-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Her kan du selv tilføje Firestore-logikken bagefter
            },
            enabled = familyName.isNotBlank() && email.isNotBlank()
        ) {
            Text("Opret")
        }
    }
}





@Composable
fun TestKnap() {
    val viewModel: TestScreenViewModel = viewModel()
    Button(onClick = {
        viewModel.upload()
    }) { Text("Test Knap") }

}

/*@Composable
fun CreateFamily() {
    var familyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Opret ny familie", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = familyName,
            onValueChange = { familyName = it },
            label = { Text("Familienavn") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Administrator e-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val familieCollection = Firebase.firestore.collection("familier") //Her defineres collection arbejdes med
                val familyData = hashMapOf(
                    "familyName" to familyName,
                    "createdBy" to email
                )

                // 1. Tilføj familie
                familieCollection
                    .add(familyData)
                    .addOnSuccessListener { familyRef ->
                        Log.d("Firestore", "Familie oprettet med ID: ${familyRef.id}")

                        // 2. Tilføj admin som første medlem
                        val memberData = hashMapOf("email" to email)
                        familyRef.collection("members")
                            .add(memberData)
                            .addOnSuccessListener {
                                showDialog = true
                            }
                            .addOnFailureListener {
                                Log.w("Firestore", "Kunne ikke tilføje medlem", it)
                            }
                    }
                    .addOnFailureListener {
                        Log.w("Firestore", "Kunne ikke oprette familie", it)
                    }
            },
            enabled = familyName.isNotBlank() && email.isNotBlank()
        ) {
            Text("Opret")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Succes") },
                text = { Text("Familien er oprettet, og administrator er tilføjet som medlem.") }
            )
        }
    }
}
 */