package org.aman.coding.presentaion.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import learncoding.composeapp.generated.resources.Res
import learncoding.composeapp.generated.resources.compose_multiplatform
import org.aman.coding.uitl.Const.APP_ICON
import org.aman.coding.uitl.Const.GOOGLE_BUTTON
import org.aman.coding.presentaion.common.InputField
import org.aman.coding.presentaion.common.PasswordInputField
import org.aman.coding.presentaion.viewmodels.UIState
import org.aman.coding.presentaion.viewmodels.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onGoogleLoginClick: () -> Unit,
    onSignUpClick:()-> Unit,
      navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    Logger.d("LoginScreen")

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginState by loginViewModel.loginState.collectAsState()
    val sting: String by loginViewModel.string1.collectAsState()

    when (loginState) {
        is UIState.Loading -> {

        }
        is UIState.Success -> {
            navController.navigate("MainScreen") {
                popUpTo("LoginScreen") {
                    inclusive = true
                }
            }
        }
        is UIState.Error -> {

        }

        UIState.Idle -> {

        }
    }


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth

        if (screenWidth > 600.dp) {
            // For larger screens
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // App logo on the left, taking half of the screen
                AsyncImage(
                    model = APP_ICON,
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillHeight
                )

                // Login form on the right
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoginForm(
                        email = email,
                        password = password,
                        passwordVisible = passwordVisible,
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it },
                        onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                        onLoginClick = { loginViewModel.login(email,password) },
                        onGoogleLoginClick = { loginViewModel.login(email,password) },
                        onSignUpClick = {},
                        navController = navController
                    )
                }
            }
        } else {
            // For smaller screens like smartphones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Centered layout
                AsyncImage(
                    model = APP_ICON,
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                LoginForm(
                    email = email,
                    password = password,
                    passwordVisible = passwordVisible,
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                    onLoginClick = { loginViewModel.login(email,password) },
                    onGoogleLoginClick = { loginViewModel.login(email,password) },
                    onSignUpClick = {  },
                    navController= navController
                )
            }
        }
    }
}


@Composable
fun LoginForm(
    email: String,
    password: String,
    passwordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    navController: NavController
) {
    // Email Field
    InputField(
        value = email,
        label = "Email",
        leadingIcon = Icons.Default.Email,
        onValueChange = onEmailChange
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Password Field
    PasswordInputField(
        value = password,
        label = "Password",
        passwordVisible = passwordVisible,
        onValueChange = onPasswordChange,
        onVisibilityToggle = onPasswordVisibilityChange
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Login Button
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Google Login Button
    Button(
        onClick = onGoogleLoginClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        AsyncImage(
            model = GOOGLE_BUTTON,
            placeholder = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape).size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Login with Google", color = Color.Black, fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Sign Up Button
    Button(
        onClick = {
            navController.navigate("MainScreen") {
                popUpTo("LoginScreen") {
                    inclusive = true
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
    ) {
        Text(text = "Sign Up", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
