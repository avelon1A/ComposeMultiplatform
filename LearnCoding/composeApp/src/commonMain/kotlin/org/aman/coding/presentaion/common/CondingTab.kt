import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aman.coding.presentaion.viewmodels.CodingViewModel

@Composable
fun CodingTab(onCompile: () -> Unit, onSubmit: () -> Unit,viewmodle: CodingViewModel) {
    var code  = viewmodle.code
    val scrollState = rememberScrollState()

    Box(){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D2D))
            ) {
                // Line numbers column with shared scroll state
                LineNumbersColumn(code, scrollState)

                Spacer(modifier = Modifier.width(8.dp))

                // Code editor with shared scroll state
                CodeEditorWithLineNumbers(code, scrollState, onCodeChange = { code = it
                viewmodle.updateCode(code)
                })
            }
        }
        FloatingActionButton(onClick = onCompile,  modifier = Modifier.padding(
            bottom = 16.dp,
            end = 16.dp
        ).align(Alignment.BottomEnd) ){
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription ="compile button")

        }
    }

}
@Composable
fun LineNumbersColumn(code: String, scrollState: androidx.compose.foundation.ScrollState) {
    val lineCount = lineCount(code)

    Column(
        modifier = Modifier
            .background(Color(0xFF1A1A1A))
            .padding(4.dp)
            .width(20.dp) // Adjust this width based on your preference
            .verticalScroll(scrollState), // Using shared scrollState
        verticalArrangement = Arrangement.Top
    ) {
        for (i in 1..lineCount) {
            Text(
                text = i.toString(),
                style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun CodeEditorWithLineNumbers(code: String, scrollState: androidx.compose.foundation.ScrollState, onCodeChange: (String) -> Unit) {
    BasicTextField(
        value = code,
        onValueChange = onCodeChange,
        textStyle = TextStyle(fontFamily = FontFamily.Monospace, color = Color.White, fontSize = 14.sp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Using shared scrollState
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    )
}


fun lineCount(code:String): Int {
    if(code.lines().size < 30){
       return 35
    }
    else{
        return code.lines().size
    }
}
