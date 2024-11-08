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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import org.aman.coding.data.Category
import org.aman.coding.uitl.NetworkError
import org.aman.screens.common.DsaScreenAndroid
import org.aman.screens.viewmodels.MainScrenViewModel
import org.aman.screens.viewmodels.UIState


@Composable
fun MainScreen(viewModel: MainScrenViewModel) {

  val uiState  = viewModel.listOfCategories.collectAsState()
  val error  = viewModel.error.collectAsState()


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        val screenWidth = maxWidth

        Column {
            Button(
                onClick = { viewModel.loadCategories()
                    viewModel.test() },
            ){
                Text(text = "Load Categories")
            }
            Text(text = error.value)
        }

        if (screenWidth > 600.dp) {
            DsaScreenAndroid(categories = uiState.value, column = 5)
        } else {
            DsaScreenAndroid(categories = uiState.value, column = 2)
        }
        }
}