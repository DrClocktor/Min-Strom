package com.example.minstrom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.ui.screens.*
import com.example.minstrom.ui.test.UsersScreen
import com.example.minstrom.ui.viewModel.UserViewModel
import com.example.minstrom.ui.viewModel.ViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: ViewModel,
    userViewModel: UserViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "planning"
    ) {
        composable("planning") { PlanningScreen(navController) }
        composable("add_plan") { AddPlanScreen(navController) }
        composable("menu") { MenuScreen(navController) }

        // Disse bruger bruger-ViewModel
        composable("users") { UsersScreen(navController, userViewModel) }
        composable("add_user") { AddUsersScreen(navController, userViewModel) }

        composable("invite_users") { InviteUsersScreen(navController) }
        composable("invite_sms") { InviteUsersSms(navController) }
        composable("invite_email") { InviteUsersEmail(navController) }
        composable("notification_test") { NotificationTestScreen(viewModel) }
    }
}


