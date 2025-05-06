package com.project.travguide.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import com.project.travguide.data.model.UserDetails
import com.project.travguide.data.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<UserDetails>>()
    val loginResult: LiveData<Result<UserDetails>> = _loginResult

    fun login(travelId: String, email: String) {
        authRepository.getUserDetails(travelId, email) { userDetails ->
            if (userDetails != null) {
                _loginResult.postValue(Result.success(userDetails))
            } else {
                _loginResult.postValue(Result.failure(Exception("Login failed: Check your credentials.")))
            }
=======
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
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38
        }
    }
}
