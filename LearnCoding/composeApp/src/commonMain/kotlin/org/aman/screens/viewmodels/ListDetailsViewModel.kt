package org.aman.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aman.coding.data.DataList
import org.aman.coding.data.Problem
import org.aman.coding.network.ApiClient
import org.aman.coding.uitl.Result

class ListDetailsViewModel(val client: ApiClient):ViewModel() {

    private val _listOfProblems: MutableStateFlow<List<Problem>> = MutableStateFlow(emptyList())
    val listOfProblems: StateFlow<List<Problem>> = _listOfProblems



    fun getProblemsByCategory(category: String) {
        viewModelScope.launch {
            when (val result = client.getProblemsByCategory(category)) {
                is Result.Success -> {
                    _listOfProblems.value = result.data
                }
                is Result.Error -> {

                }


            }
        }
    }
}