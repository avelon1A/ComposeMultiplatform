package org.aman.coding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.engine.okhttp.OkHttp
import org.aman.coding.network.ApiClient
import org.aman.coding.network.createHttpClient
import org.aman.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(client = remember{
                ApiClient(createHttpClient(OkHttp.create()))
            })
        }
    }
}

@Preview(
    name = "Laptop Screen",
    widthDp = 1366, // Width in dp for laptop screen
    heightDp = 768, // Height in dp for laptop screen

)
@Composable
fun AppAndroidLaptopPreview() {
    LoginScreen(
        onLoginClick = { email, password -> },
        onGoogleLoginClick = {},
        onSignUpClick = {}
    )
}

//@Preview(
//    name = "Phone Screen",
//    showBackground = true
//)
//@Composable
//fun AppAndroidPhonePreview() {
//    LoginScreen(
//        onLoginClick = { email, password -> },
//        onGoogleLoginClick = {},
//        onSignUpClick = {}
//    )
//}
