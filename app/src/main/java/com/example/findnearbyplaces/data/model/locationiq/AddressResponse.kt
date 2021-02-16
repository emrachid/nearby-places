package com.example.findnearbyplaces.data.model.locationiq

data class AddressResponse (
    val address: Address,
    val boundingbox: List<String>,
    val display_name: String,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: String,
    val osm_type: String,
    val place_id: String
)