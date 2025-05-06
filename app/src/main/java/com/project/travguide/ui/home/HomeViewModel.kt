package com.project.travguide.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.travguide.data.model.OpenTripFeature
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.data.repository.NetworkRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val networkRepository: NetworkRepository,
    private val travelId: String,
    private val email: String
) : ViewModel() {

    private val _touristSpots = MutableLiveData<List<OpenTripFeature>>()
    val touristSpots: LiveData<List<OpenTripFeature>> = _touristSpots

    private val _destination = MutableLiveData<String>()
    val destination: LiveData<String> = _destination

    private val _tNumber = MutableLiveData<String>()
    val tNumber: LiveData<String> = _tNumber

    init {
        fetchUserDetailsAndTouristSpots()
    }

    private fun fetchUserDetailsAndTouristSpots() {
        authRepository.getUserDetails(travelId, email) { user ->
            user?.let {
                _destination.postValue(it.destination)
                _tNumber.postValue(it.tNumber)

                viewModelScope.launch {
                    try {
                        val coordinates = networkRepository.getCoordinates(it.destination)
                        coordinates?.let { (lat, lon) ->
                            val spots = networkRepository.getTouristSpots(lat, lon)
                            _touristSpots.postValue(spots)
                        } ?: run {
                            Log.e("HomeViewModel", "Coordinates not found for destination: ${it.destination}")
                        }
                    } catch (e: Exception) {
                        Log.e("HomeViewModel", "Error fetching tourist spots: ${e.message}")
                    }
                }
            } ?: run {
                Log.e("HomeViewModel", "User details not found for travelId=$travelId")
            }
        }
    }
}
