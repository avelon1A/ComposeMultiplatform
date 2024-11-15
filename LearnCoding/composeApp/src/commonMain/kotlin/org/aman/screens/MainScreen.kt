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
import org.aman.screens.common.DsaScreenAndroid
import org.aman.screens.viewmodels.MainScrenViewModel


@Composable
fun MainScreen(viewModel: MainScrenViewModel, onCategoryClick: (Any?) -> Unit) {

    val uiState = viewModel.listOfCategories.collectAsState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth
        Column {
            Button(onClick = {viewModel.getDataList()}){
                Text("Load Categories")
            }

            if (screenWidth > 600.dp) {
                DsaScreenAndroid(categories = uiState.value.data, column = 5,onCategoryClick=  { onCategoryClick("array")})
            } else {
                DsaScreenAndroid(categories = uiState.value.data, column = 2,onCategoryClick = { onCategoryClick("array")})
            }
        }

    }
}

