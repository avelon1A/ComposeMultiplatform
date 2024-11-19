package org.aman.coding.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.aman.coding.domain.model.respnonse.Problem
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.uitl.Result

class ListDetailsViewModel(val client: ApiClient):ViewModel() {

    private val _listOfProblems: MutableStateFlow<List<Problem>?> = MutableStateFlow(null)


    val listOfProblems = _listOfProblems.onStart { getProblemsByCategory("array") }.stateIn(viewModelScope, initialValue = emptyList(),
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000))


    fun getProblemsByCategory(category: String) {
        if (_listOfProblems.value.isNullOrEmpty() ){
            Logger.d("getProblemsByCategory")
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


}