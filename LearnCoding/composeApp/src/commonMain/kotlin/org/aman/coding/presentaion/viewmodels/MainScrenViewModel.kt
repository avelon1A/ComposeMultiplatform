package org.aman.coding.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aman.coding.domain.model.appModel.DataList
import org.aman.coding.data.remote.ApiClient


class MainScrenViewModel(val client: ApiClient):ViewModel() {

    private val _ListOfCategories: MutableStateFlow<DataList> = MutableStateFlow(DataList(emptyList(),0))
    val listOfCategories: StateFlow<DataList> = _ListOfCategories

    init {
        getDataList()
    }

//    val listOfCategories = _ListOfCategories.onStart { getDataList() }.stateIn(viewModelScope, initialValue = false,
//        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000)
//    )
    fun getDataList() {
        Logger.d("getDataList")
        viewModelScope.launch {
            try {
                _ListOfCategories.value =    client.getDataList()
            }
            catch (_:Exception){

            }
        }
    }

}