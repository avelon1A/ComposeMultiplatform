package org.aman.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import learncoding.composeapp.generated.resources.Res
import learncoding.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onGoogleLoginClick: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
        AsyncImage(
            model = "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/coding%20Main%20image.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvY29kaW5nIE1haW4gaW1hZ2UucG5nIiwiaWF0IjoxNzMwOTc3NzYzLCJleHAiOjE3MzM1Njk3NjN9.q2P0ihQ1PJE1hE9O45Ow3Vn88IZxv7AojQkBYdLJjqw&t=2024-11-07T11%3A08%3A31.963Z",
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) {
                            "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/visible.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvdmlzaWJsZS5wbmciLCJpYXQiOjE3MzA5NjE4NjcsImV4cCI6MTczMTU2NjY2N30.ZFBHvWxDSaz18B9uMdVFL1vSstMEgWC7jJO1enEzzrw&t=2024-11-07T06%3A43%3A36.390Z"
                        } else {
                            "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/hidden_3694208.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvaGlkZGVuXzM2OTQyMDgucG5nIiwiaWF0IjoxNzMwOTYxODUyLCJleHAiOjE3MzE1NjY2NTJ9.BjK6kowJYheNkeUfyFH1syq2CEnDE4nBzNg-VNw5xx8&t=2024-11-07T06%3A43%3A21.656Z"
                        }

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = rememberAsyncImagePainter(model = image),
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )


        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password ClickableText
        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.body2.copy(
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = { onLoginClick(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider or OR separator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
            Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google Login Button
        Button(
            onClick = { onGoogleLoginClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            elevation = ButtonDefaults.elevation(8.dp)
        ) {
            AsyncImage(
                model = "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/new%20google.webp?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMgYXNzZXRzL2xvZ2luIHBhZ2UvbmV3IGdvb2dsZS53ZWJwIiwiaWF0IjoxNzMwOTc3NDAzLCJleHAiOjE3MzM1Njk0MDN9.-M9A5NBeZnGGAPwKkmKkbPG6gisFTkZgkjJYkPbtFpc&t=2024-11-07T11%3A02%3A32.176Z",
                placeholder = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Login with Google", color = Color.Black, fontSize = 16.sp)
        }
    }
}
