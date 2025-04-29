package com.example.minstrom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.minstrom.ui.theme.MinStromTheme
import com.example.minstrom.ui.familie.TestKnap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestKnap()
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinStromTheme {
        TestKnap()
    }
}