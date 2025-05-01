package com.example.minstrom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.ui.screens.NotificationTestScreen
import com.example.minstrom.ui.screens.*
import com.example.minstrom.ui.viewModel.ViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: ViewModel) {
    NavHost(
        navController = navController,
        startDestination = "planning"
    ) {
        composable("invite_users") { InviteUsersScreen(navController) }
        composable("invite_sms") { InviteUsersSms(navController) }
        composable("invite_email") { InviteUsersEmail(navController) }
        composable("notification_test") { NotificationTestScreen(viewModel) }
        composable("planning") { PlanningScreen(navController) }
        composable("menu") { MenuScreen(navController) }
        composable("add_plan") { AddPlanScreen(navController) }
    }
}

