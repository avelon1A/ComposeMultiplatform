package org.aman.coding

import CodingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.data.remote.createHttpClient
import org.aman.coding.presentaion.viewmodels.CodingViewModel

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
//@Composable
//fun AppAndroidLaptopPreview() {
//    LoginScreen(
//        onLoginClick = { email, password -> },
//        onGoogleLoginClick = {},
//        onSignUpClick = {},
//        loggedIn = {}
//    )
//}

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
@Preview(
    name = "Laptop Screen 1",
    widthDp = 1366,
    heightDp = 768,

    )
@Composable
fun CodingScreenPreview() {
    CodingScreen(viewModel = CodingViewModel(ApiClient(httpClient = HttpClient())))
}