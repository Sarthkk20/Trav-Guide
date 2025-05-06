package com.project.travguide.network.api

import com.project.travguide.data.model.GeoResponse
import com.project.travguide.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApiService {
    @GET("searchJSON")
    suspend fun getCoordinates(
        @Query("q") city: String,
        @Query("maxRows") maxRows: Int = 1,
        @Query("username") username: String = Constants.GEO_NAMES_USERNAME
    ): GeoResponse
}

