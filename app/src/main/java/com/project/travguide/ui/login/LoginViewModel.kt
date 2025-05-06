package com.project.travguide.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        }
    }
}
