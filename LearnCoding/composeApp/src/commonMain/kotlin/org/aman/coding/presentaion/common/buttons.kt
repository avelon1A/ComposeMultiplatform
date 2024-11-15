package org.aman.coding.presentaion.common
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter

@Composable
fun InputField(
    value: String,
    label: String,
    leadingIcon: ImageVector,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(leadingIcon, contentDescription = "$label Icon") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Composable
fun PasswordInputField(
    value: String,
    label: String,
    passwordVisible: Boolean,
    onValueChange: (String) -> Unit,
    onVisibilityToggle: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) {
                "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/visible.png"
            } else {
                "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/hidden_3694208.png"
            }
            IconButton(onClick = onVisibilityToggle) {
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
}

@Composable
fun SocialSignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        AsyncImage(
            model = "https://jqnqidbwhiycqvbffyba.supabase.co/storage/v1/object/sign/images%20assets/login%20page/new%20google.webp",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape).size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Sign Up with Google", color = Color.Black, fontSize = 16.sp)
    }
}
