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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import org.aman.coding.presentaion.common.InputField
import org.aman.coding.presentaion.common.PasswordInputField
import org.aman.coding.presentaion.common.SocialSignUpButton

@Composable
fun SignUpScreen(
    onSignUpClick: (name: String, email: String, password: String) -> Unit,
    onGoogleSignUpClick: () -> Unit,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth

        // Layout adjustment based on screen size
        val content: @Composable () -> Unit = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/coding%20Main%20image.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvY29kaW5nIE1haW4gaW1hZ2UucG5nIiwiaWF0IjoxNzMwOTc3NzYzLCJleHAiOjE3MzM1Njk3NjN9.q2P0ihQ1PJE1hE9O45Ow3Vn88IZxv7AojQkBYdLJjqw&t=2024-11-07T11%3A08%3A31.963Z",
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                SignUpForm(
                    name = name,
                    email = email,
                    password = password,
                    passwordVisible = passwordVisible,
                    onNameChange = { name = it },
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                    onSignUpClick = { onSignUpClick(name,email,password) },
                    onGoogleSignUpClick = onGoogleSignUpClick,
                    onLoginClick =    { navController.navigate("Login") }
                )
            }
        }

        if (screenWidth > 600.dp) {
            // Tablet/Desktop Layout
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/coding%20Main%20image.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvY29kaW5nIE1haW4gaW1hZ2UucG5nIiwiaWF0IjoxNzMwOTc3NzYzLCJleHAiOjE3MzM1Njk3NjN9.q2P0ihQ1PJE1hE9O45Ow3Vn88IZxv7AojQkBYdLJjqw&t=2024-11-07T11%3A08%3A31.963Z",
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    contentScale = ContentScale.Fit
                )
                content()
            }
        } else {
            // Mobile Layout
            content()
        }
    }
}

@Composable
fun SignUpForm(
    name: String,
    email: String,
    password: String,
    passwordVisible: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    InputField(
        value = name,
        label = "Name",
        leadingIcon = Icons.Default.Person,
        onValueChange = onNameChange
    )

    Spacer(modifier = Modifier.height(16.dp))

    InputField(
        value = email,
        label = "Email",
        leadingIcon = Icons.Default.Email,
        onValueChange = onEmailChange
    )

    Spacer(modifier = Modifier.height(16.dp))

    PasswordInputField(
        value = password,
        label = "Password",
        passwordVisible = passwordVisible,
        onValueChange = onPasswordChange,
        onVisibilityToggle = onPasswordVisibilityChange
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onSignUpClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "Sign Up", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Google Sign-Up Button
    SocialSignUpButton(onClick = onGoogleSignUpClick)

    Spacer(modifier = Modifier.height(16.dp))

    // Login Button
    TextButton(onClick = onLoginClick) {
        Text(text = "Already have an account? Log in", fontSize = 16.sp)
    }
}
