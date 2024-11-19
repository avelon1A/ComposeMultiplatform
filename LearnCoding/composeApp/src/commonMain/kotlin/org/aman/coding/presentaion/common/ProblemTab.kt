package org.aman.coding.presentaion.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.style.TextAlign
import org.aman.coding.domain.model.respnonse.Example
import org.aman.coding.domain.model.respnonse.ProblemDetails


@Composable
fun ProblemTab(problemDetails: ProblemDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Problem #${problemDetails.id}",
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = problemDetails.description,
            style = MaterialTheme.typography.body1.copy(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display each example inside a highlighted box
        problemDetails.examples.forEach { example ->
            ExampleBox(example = example)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ExampleBox(example: Example) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color(0xFF333366),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Input part
            Text(
                text = "Input:",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = example.input,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 14.sp,
                    color = Color.LightGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Output part
            Text(
                text = "Output:",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = example.output,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 14.sp,
                    color = Color.LightGray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Explanation part
            Text(
                text = "Explanation:",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = example.explanation,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 14.sp,
                    color = Color.LightGray
                ),
                textAlign = TextAlign.Justify
            )
        }
    }
}
