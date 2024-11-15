package org.aman.coding.presentaion.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aman.coding.presentaion.viewmodels.CodingViewModel
import org.aman.coding.presentaion.viewmodels.UIState

@Composable
fun ResultTab(
    result: String,
    compileResult: String,
    onSubmit: () -> Unit,
    viewModel: CodingViewModel
) {
    val compileResult1 by viewModel.compileResult.collectAsState()

    val expandedCompileResultHeight by animateDpAsState(
        targetValue = if (compileResult1 is UIState.Loading) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 300)
    )

    val expandedExecutionResultHeight by animateDpAsState(
        targetValue = if (result.length < 100) 100.dp else 200.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D))
            .padding(16.dp)
    ) {
        Text(
            text = "Compiling Result",
            style = MaterialTheme.typography.h6.copy(color = Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1A1A))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (compileResult1) {
                is UIState.Success -> {
                    val successState = compileResult1 as UIState.Success
                    Text(
                        text = successState.data.toString(),
                        style = MaterialTheme.typography.body1.copy(color = Color.LightGray),
                        fontSize = 16.sp
                    )
                }
                is UIState.Error -> {
                    val errorState = compileResult1 as UIState.Error
                    Text(
                        text = errorState.message.toString(),
                        color = Color.Red,
                        style = MaterialTheme.typography.body1
                    )
                }
                UIState.Idle -> {
                    Text(
                        text = "Awaiting compilation",
                        style = MaterialTheme.typography.body1.copy(color = Color.LightGray),
                        fontSize = 16.sp
                    )
                }
                UIState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Execution Result",
            style = MaterialTheme.typography.h6.copy(color = Color.White, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1A1A))
                .padding(16.dp)
                .height(expandedExecutionResultHeight)
        ) {
            Text(
                text = result,
                style = MaterialTheme.typography.body1.copy(color = Color.LightGray),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button (optional)
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun ResultTabScreen(compilerResult: String, viewModel: CodingViewModel) {
    val result = remember { mutableStateOf("The result of your code execution will be shown here.") }

    ResultTab(
        result = result.value,
        onSubmit = {
            // Implement your submit logic here
        },
        compileResult = compilerResult,
        viewModel = viewModel
    )
}
