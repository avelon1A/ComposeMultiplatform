package org.aman.coding.uitl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.browser.window

@Composable
actual fun handleBackNavigation(navController: NavHostController) {
    LaunchedEffect(Unit) {
        window.onpopstate = {
            navController.popBackStack()
        }
        window.history.pushState(null, "", window.location.href)
        window.history.forward()
        window.history.back()

    }
}