package com.talp.smartteacher.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TalpNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TalpRoute.LoginScreen.route
    ) {
        composable(TalpRoute.LoginScreen.route) {
            // LoginScreen(navController)
        }
        composable(TalpRoute.TeacherDashboard.route) {
            // TeacherDashboard(navController)
        }
        composable(TalpRoute.StudentDashboard.route) {
            // StudentDashboard(navController)
        }
        // Add more routes here
    }
}
