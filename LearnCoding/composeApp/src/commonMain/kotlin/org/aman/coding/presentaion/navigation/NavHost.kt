package org.aman.coding.presentation.navigation

import CodingScreen
import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.presentaion.screens.ListDetailsScreen
import org.aman.coding.presentaion.screens.LoginScreen
import org.aman.coding.presentaion.screens.MainScreen

@Composable
fun NavigationHost(navController: NavHostController, client: ApiClient) {

    NavHost(navController, startDestination = "Login") {
        composable("Login") {
            LoginScreen(
                onLoginClick = { email, password -> },
                onGoogleLoginClick = {},
                onSignUpClick = { },
                navController = navController,
            )
        }
        composable("Signup") {
            SignUpScreen(
                onSignUpClick = { name, email, password -> },
                onGoogleSignUpClick = {},
                navController = navController,
            )
        }
        composable("MainScreen") {
            MainScreen(
                onCategoryClick = { category -> navController.navigate("ListScreen/$category") }
            )
        }
        composable("ListScreen/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            ListDetailsScreen(
                category = category ?: "",
                onListClick = { navController.navigate("CodingScreen") }
            )
        }
        composable("CodingScreen") {
            CodingScreen()
        }
    }
}
