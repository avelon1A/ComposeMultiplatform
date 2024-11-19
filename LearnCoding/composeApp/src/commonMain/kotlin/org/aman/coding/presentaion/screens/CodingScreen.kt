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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.aman.coding.presentaion.common.ProblemTab
import org.aman.coding.presentaion.viewmodels.CodingViewModel
import org.aman.coding.presentaion.viewmodels.UIState
import org.aman.coding.presentaion.common.ResultTabScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CodingScreen(viewModel: CodingViewModel = koinViewModel()) {
    val compileResult by viewModel.compileResult.collectAsState()
    val detialedProblem by viewModel.detialedProblem.collectAsState()
    val compilerText  =   remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { CodingTabs.entries.size })
    val selectedTab = remember { derivedStateOf { pagerState.currentPage } }


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth

        if (screenWidth > 600.dp) {

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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (compileResult) {
                            is UIState.Success -> {
                                val successState = compileResult as UIState.Success
                                compilerText.value = successState.data.toString()
                                Text(
                                    text = successState.data.toString(),
                                    color = MaterialTheme.colors.background
                                )
                            }

                            is UIState.Error -> {
                                val errorState = compileResult as UIState.Error
                                Text(text = errorState.message.toString(), color = Color.Red)
                            }

                            UIState.Idle -> {
                                Text(
                                    text = "Awaiting compilation",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.background
                                )
                            }

                            UIState.Loading -> {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    ActionButtons(
                        onCompile = { viewModel.compileCode() },
                        onSubmit = { viewModel.submitCode() }
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = selectedTab.value, modifier = Modifier.fillMaxWidth()
                ) {
                    CodingTabs.entries.forEachIndexed { index, entry ->

                        Tab(modifier = Modifier
                            .height(60.dp)
                            .background(color = Color(0xFF2D2D2D)),
                            selected = selectedTab.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(entry.ordinal)
                                }
                            },
                            text = { Text(text = entry.title) },
                          )

                    }


                }
                HorizontalPager(
                    state = pagerState, modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    when (CodingTabs.entries[pageIndex]) {
                        CodingTabs.PROBLEM -> detialedProblem?.let { ProblemTab(problemDetails = it) }
                        CodingTabs.CODE -> CodingTab(onCompile = { viewModel.compileCode() }, onSubmit = { viewModel.submitCode()  },viewmodle = viewModel)
                        CodingTabs.RESULT -> ResultTabScreen(compilerText.value,viewModel)
                    }
                }

            }

        }
    }
}

@Composable
fun ProblemTab1() {
    Column {  }
}

enum class CodingTabs(val title:String) {
    PROBLEM("Problem"),
    CODE("Code"),
    RESULT("Result")

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

