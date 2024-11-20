package org.aman.coding.uitl

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
actual fun handleBackNavigation(navController: NavHostController) {
    BackHandler {
        if (navController.currentBackStackEntry != null && navController.popBackStack().not()) {
        }
    }
}