package org.aman.coding.presentaion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.aman.coding.presentaion.common.ListScreenAndroid
import org.aman.coding.presentaion.viewmodels.ListDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun ListDetailsScreen(viewModel: ListDetailsViewModel = koinViewModel(), category: String, onListClick: () -> Unit) {
 co.touchlab.kermit.Logger.d("ListDetailsScreen")
    val uiState by viewModel.listOfProblems.collectAsState()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth
        Column {

            if (screenWidth > 600.dp) {
                uiState?.let {
                    ListScreenAndroid(
                        problem = it,
                        column = 5,
                        onListClick = { onListClick() })
                }
            } else {
                uiState?.let {
                    ListScreenAndroid(
                        problem = it,
                        column = 2,
                        onListClick = { onListClick() })
                }
            }
        }

    }
}