package com.example.findnearbyplaces.data.model.maps.geocode

import com.example.findnearbyplaces.data.model.maps.PlusCode

data class ReverseGeocodingResponse(
    val plus_code: PlusCode,
    val results: List<Result>,
    val status: String
)