package com.example.minstrom


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.minstrom.ui.screens.InviteUsersScreen
import com.example.minstrom.ui.theme.MinStromTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.ui.screens.InviteUsersEmail
import com.example.minstrom.ui.screens.InviteUsersSms

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                Surface {
                    NavHost(navController = navController, startDestination = "invite_users") {
                        composable("invite_users") {
                            InviteUsersScreen(navController) // Send navController
                        }
                        composable("invite_sms") {
                            InviteUsersSms(navController)
                        }
                        composable("invite_email") { //  Ny side til e-mail invitation
                            InviteUsersEmail(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinStromTheme {
        Greeting("Android")
    }
}