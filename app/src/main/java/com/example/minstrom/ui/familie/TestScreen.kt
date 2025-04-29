package com.example.minstrom.ui.familie

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minstrom.ui.viewModel.TestScreenViewModel

@Composable
fun TestKnap() {
    val viewModel: TestScreenViewModel = viewModel()
    Button(onClick = {
        viewModel.upload()
    }) { Text("Test Knap") }

}