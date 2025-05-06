package com.project.travguide.network.api

import com.project.travguide.data.model.FeatureCollection
import com.project.travguide.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTripApiService {
    @GET("0.1/en/places/radius")
    suspend fun getTouristSpots(
        @Query("lon") lon: Double?,
        @Query("lat") lat: Double?,
        @Query("radius") radius: Int = 1000,
        @Query("format") format: String = "geojson",
        @Query("apikey") apiKey: String = Constants.OPEN_TRIP_API_KEY
    ): FeatureCollection
}
