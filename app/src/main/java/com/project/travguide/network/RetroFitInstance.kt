package com.project.travguide.network

import com.project.travguide.network.api.GeoApiService
import com.project.travguide.network.api.OpenTripApiService
import com.project.travguide.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val openTripApi: OpenTripApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.OPEN_TRIP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenTripApiService::class.java)
    }

    val geoApi: GeoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.GEO_NAMES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeoApiService::class.java)
    }
}
