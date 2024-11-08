package org.aman.coding

import SignUpScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.aman.coding.network.ApiClient
import org.aman.screens.LoginScreen
import org.aman.screens.MainScreen
import org.aman.screens.viewmodels.LoginViewModel
import org.aman.screens.viewmodels.MainScrenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(client: ApiClient) {

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "Login") {
            composable("Login") {
                LoginScreen(onLoginClick = { email, password ->}, onGoogleLoginClick = {},
                    onSignUpClick = { navController.navigate("MainScreen") }, loginViewModel = LoginViewModel(client)
                )
            }
            composable("Signup") {
                SignUpScreen(onSignUpClick = { name, email, password ->},
                    onGoogleSignUpClick = {},
                    onLoginClick = { navController.navigate("Login")})
            }
            composable("MainScreen"){
                MainScreen(viewModel = MainScrenViewModel(client))
            }
        }

    }
}
