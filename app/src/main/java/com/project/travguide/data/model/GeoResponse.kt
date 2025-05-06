package com.project.travguide.data.model

data class GeoResponse(
    val geonames: List<GeoName>
)

data class GeoName(
    val lat: String,
    val lng: String,
    val name: String? = null
)