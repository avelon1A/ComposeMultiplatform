package org.aman.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.aman.coding.network.ApiClient


class CodingViewModel(val client: ApiClient):ViewModel() {

    var code by mutableStateOf("")

    fun updateCode(newCode: String) {
        code = newCode
    }

    fun compileCode() {

    }

    fun submitCode() {

    }
}