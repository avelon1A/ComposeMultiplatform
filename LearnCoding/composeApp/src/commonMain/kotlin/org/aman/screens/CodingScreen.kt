import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aman.screens.viewmodels.CodingViewModel

@Composable
fun CodingScreen(viewModel: CodingViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF2D2D2D), shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            ProblemDescriptionSection(
                description = "Given an unsorted array `arr` containing only non-negative integers, your task is to find a continuous subarray whose sum equals a specified value target. You need to return the 1-based indices of the leftmost and rightmost elements of this subarray.",
                examples = listOf(
                    "Input: arr[] = [1,2,3,7,5], target = 12\nOutput: [2, 4]",
                    "Input: arr[] = [1,2,3,4,5,6,7,8,9,10], target = 15\nOutput: [1, 5]"
                )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(16.dp)
                .background(Color(0xFF2D2D2D), shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            CodeEditorSection(
                code = viewModel.code,
                onCodeChange = { viewModel.updateCode(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                onCompile = { viewModel.compileCode() },
                onSubmit = { viewModel.submitCode() }
            )
        }
    }
}

@Composable
fun ProblemDescriptionSection(description: String, examples: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Indexes of Subarray Sum",
            style = MaterialTheme.typography.h6.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1.copy(color = Color.LightGray),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        examples.forEach { example ->
            Text(
                text = example,
                style = MaterialTheme.typography.body2.copy(color = Color.LightGray),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CodeEditorSection(code: String, onCodeChange: (String) -> Unit) {
    Text(
        text = "Code Editor",
        style = MaterialTheme.typography.h6.copy(color = Color.White),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
        value = code,
        onValueChange = onCodeChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        textStyle = TextStyle(fontFamily = FontFamily.Monospace, color = Color.White),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            backgroundColor = Color(0xFF1A1A1A),
            cursorColor = Color.White
        ),
        maxLines = 20
    )
}

@Composable
fun ActionButtons(onCompile: () -> Unit, onSubmit: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onCompile,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Compile & Run")
        }
        Button(
            onClick = onSubmit,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Submit")
        }
    }
}

