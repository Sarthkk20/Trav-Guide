package com.project.travguide.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.data.repository.NetworkRepository

class HomeViewModelFactory(
    private val authRepository: AuthRepository,
    private val networkRepository: NetworkRepository,
    private val travelId: String,
    private val email: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(authRepository, networkRepository, travelId, email) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
