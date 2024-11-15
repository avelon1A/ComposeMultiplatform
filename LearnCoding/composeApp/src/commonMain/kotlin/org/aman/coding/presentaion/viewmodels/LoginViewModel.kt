package org.aman.coding.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.aman.coding.data.remote.ApiClient
import org.aman.coding.uitl.NetworkError
import org.aman.coding.uitl.onError
import org.aman.coding.uitl.onSuccess

class LoginViewModel(val client: ApiClient) : ViewModel() {


    private val _loginState = MutableStateFlow<UIState>(UIState.Idle)
    val loginState: StateFlow<UIState> = _loginState

    private val _sting: MutableStateFlow<String> = MutableStateFlow("")
    val string1 = _sting.asStateFlow()


    fun login(email: String, password: String) {
        _loginState.value = UIState.Loading
        viewModelScope.launch {
            client.login(email, password)
                .onSuccess {
                    _loginState.value = UIState.Success(it)
                }
                .onError {
                    _loginState.value = UIState.Error(it)

                }
        }


    }
}

    sealed class UIState {
        object Idle : UIState()
        object Loading : UIState()
        data class Success(val data: Any) : UIState()
        data class Error(val message: NetworkError) : UIState()
    }
