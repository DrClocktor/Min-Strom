package com.example.minstrom.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.minstrom.ui.test.CreateFamily
//import com.example.minstrom.data.model.ViewModel
import com.example.minstrom.ui.test.TestKnap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //TestKnap()
            CreateFamily()
        }
    }
}

//