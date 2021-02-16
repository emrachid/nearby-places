package com.example.findnearbyplaces.data.model.maps.geocode

import com.example.findnearbyplaces.data.model.maps.PlusCode

data class Result(
    val address_components: List<AddressComponent>,
    val formatted_address: String,
    val geometry: Geometry,
    val place_id: String,
    val plus_code: PlusCode,
    val types: List<String>
)