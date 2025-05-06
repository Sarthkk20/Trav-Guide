package com.project.travguide.data.model

data class FeatureCollection(
    val type: String,
    val features: List<OpenTripFeature>
)

data class OpenTripFeature(
    val id: Long,
    val geometry: Geometry,
    val properties: Properties
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

data class Properties(
    val xid: String,
    val name: String,
    val wikipedia: String? = null
)
