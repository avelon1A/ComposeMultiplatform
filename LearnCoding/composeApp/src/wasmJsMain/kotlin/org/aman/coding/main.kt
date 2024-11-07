package org.aman.coding

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.aman.screens.LoginScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
//        LoginScreen(  onLoginClick = { email, password -> },
//            onGoogleLoginClick = {
//                println("Google Login button clicked")
//            }
//        )
        App()
    }
}