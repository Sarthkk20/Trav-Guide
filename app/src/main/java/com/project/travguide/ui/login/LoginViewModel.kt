package com.project.travguide.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.utils.UiState

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _loginState = MutableLiveData<UiState<Pair<String, String>>>()
    val loginState: LiveData<UiState<Pair<String, String>>> = _loginState

    fun login(travelId: String, email: String) {
        _loginState.value = UiState.Loading
        repository.loginWithTravelIdAndEmail(travelId, email) { state ->
            _loginState.postValue(state)
        }
    }
}
