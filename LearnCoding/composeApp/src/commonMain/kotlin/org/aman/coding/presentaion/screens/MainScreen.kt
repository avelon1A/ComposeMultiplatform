package org.aman.coding.presentaion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.aman.coding.presentaion.common.DsaScreenAndroid
import org.aman.coding.presentaion.viewmodels.MainScrenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainScreen(viewModel: MainScrenViewModel = koinViewModel(), onCategoryClick: (Any?) -> Unit) {
    Logger.d("MainScreen")
    LaunchedEffect(Unit) {
        Logger.d("MainScreen first composition")
    }
    val uiState = remember { viewModel.listOfCategories }.collectAsState()

    when(val state = uiState.value){
        else -> {
            state.data
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth
        Column {

            if (screenWidth > 600.dp) {
                when(val state = uiState.value){
                    else -> {
                        DsaScreenAndroid(
                            categories = state.data,
                            column = 5,
                            onCategoryClick = { onCategoryClick("array") })
                    }
                }

            } else {
                when(val state = uiState.value){
                    else -> {
                        DsaScreenAndroid(
                            categories = state.data,
                            column = 2,
                            onCategoryClick = { onCategoryClick("array") })
                    }
                }
            }
        }

    }
}

