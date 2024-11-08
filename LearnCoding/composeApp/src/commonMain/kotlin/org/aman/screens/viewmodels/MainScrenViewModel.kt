package org.aman.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aman.coding.data.Category
import org.aman.coding.data.getMockCategories
import org.aman.coding.network.ApiClient
import org.aman.coding.uitl.NetworkError
import org.aman.coding.uitl.onError
import org.aman.coding.uitl.onSuccess


class MainScrenViewModel(val client: ApiClient):ViewModel() {

    private val _ListOfCategories = MutableStateFlow<List<Category>>(emptyList())
    val listOfCategories: StateFlow<List<Category>> = _ListOfCategories

    val _errro = MutableStateFlow<String>("test")
    val error: StateFlow<String> = _errro



    fun loadCategories() {
        viewModelScope.launch {
//            _errro.value = "loading"
            client.loadCategories()
                .onSuccess {
                    _ListOfCategories.value = it
//                    _errro.value = it.toString()
                }
                .onError {
                    _ListOfCategories.value = getMockCategories()
//                    _errro.value = it.toString()
                }
        }

    }
    fun test() {
        viewModelScope.launch {
            _errro.value = "loading"
            client.test()
                .onSuccess {
                    _errro.value = it.toString()
                }
                .onError {
                    _errro.value = it.toString()
                }
        }

    }


}