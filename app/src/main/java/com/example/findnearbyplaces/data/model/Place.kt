package com.example.findnearbyplaces.data.model

import com.example.findnearbyplaces.data.model.maps.place.Photo

data class Place(
    val business_status: String?,
    val name: String,
    val photos: List<Photo>?,
    val distance: Double
)