package org.aman.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aman.coding.data.DataList
import org.aman.coding.network.ApiClient


class MainScrenViewModel(val client: ApiClient):ViewModel() {

    private val _ListOfCategories: MutableStateFlow<DataList> = MutableStateFlow(DataList(emptyList(),0))
    val listOfCategories: StateFlow<DataList> = _ListOfCategories

    fun getDataList() {
        viewModelScope.launch {
            try {
                _ListOfCategories.value =    client.getDataList()
                Logger.i { "${_ListOfCategories.value}" }
            }
            catch (_:Exception){

            }
        }
    }

}