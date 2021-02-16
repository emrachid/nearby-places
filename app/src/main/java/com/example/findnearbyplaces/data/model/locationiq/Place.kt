package com.example.findnearbyplaces.data.model.locationiq

data class Place(
    val distance: Int,
    val lat: String,
    val lon: String,
    val name: String?,
    val osm_id: String,
    val osm_type: String,
    val tag_type: String
)