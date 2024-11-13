package org.aman.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.aman.screens.common.ListScreenAndroid
import org.aman.screens.viewmodels.ListDetailsViewModel


@Composable
fun  ListDetailsScreen(viewModel: ListDetailsViewModel,category: String,onListClick: () -> Unit) {

    val uiState = viewModel.listOfProblems.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth
        Column {
            Button(onClick = {viewModel.getProblemsByCategory(category)}){
                Text("Load Categories")
            }

            if (screenWidth > 600.dp) {
                ListScreenAndroid( problem = uiState.value, column = 5,onListClick=  { onListClick() })
            } else {
                ListScreenAndroid(problem = uiState.value, column = 2,onListClick = { onListClick() })
            }
        }

    }
}