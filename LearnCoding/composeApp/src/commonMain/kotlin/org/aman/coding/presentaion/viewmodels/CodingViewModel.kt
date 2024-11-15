package org.aman.coding.presentaion.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.domain.model.respnonse.ProblemDetails
import org.aman.coding.uitl.onError
import org.aman.coding.uitl.onSuccess


class CodingViewModel(val client: ApiClient):ViewModel() {

    private val _compileResult = MutableStateFlow<UIState>(UIState.Idle)
    val compileResult: StateFlow<UIState> = _compileResult

    private val _detiledProblem = MutableStateFlow<ProblemDetails?>(null)
    val detialedProblem: StateFlow<ProblemDetails?> = _detiledProblem
    init {
        getDetailedProblem()
    }

    var code by mutableStateOf("")

    fun updateCode(newCode: String) {
        code = newCode
    }

    fun compileCode() {
        _compileResult.value = UIState.Loading
        viewModelScope.launch {
         client.compileCode(code).onSuccess {
             _compileResult.value = UIState.Success(it)
          }
             .onError {
                 _compileResult.value = UIState.Error(it)
             }

        }
    }

    private fun getDetailedProblem(){
      viewModelScope.launch {
          Logger.d("getDetailedProblem")
          client.getDetailedProblem(id = 1,"array").onSuccess {
              _detiledProblem.value = it
          }.onError {
              _detiledProblem.value = null
          }
      }
    }

    fun submitCode() {

    }
}