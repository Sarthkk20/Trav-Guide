package com.project.travguide.data.repository

import com.project.travguide.data.model.GeoResponse
import com.project.travguide.data.model.OpenTripFeature
import com.project.travguide.network.api.GeoApiService
import com.project.travguide.network.api.OpenTripApiService

class NetworkRepository(
    private val geoApi: GeoApiService,
    private val openTripApi: OpenTripApiService
) {

    suspend fun getCoordinates(destination: String): Pair<Double, Double>? {
        val response: GeoResponse = geoApi.getCoordinates(destination)
        val firstResult = response.geonames.firstOrNull()

        return if (firstResult != null) {
            Pair(firstResult.lat.toDouble(), firstResult.lng.toDouble())
        } else null
    }

    suspend fun getTouristSpots(lat: Double, lon: Double): List<OpenTripFeature> {
        val featureCollection = openTripApi.getTouristSpots(lat, lon)
        return featureCollection.features
    }
}
