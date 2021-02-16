package com.example.findnearbyplaces.data.model.maps.place

import com.example.findnearbyplaces.data.model.maps.PlusCode

data class Place(
    val business_status: String,
    val geometry: Geometry,
    val icon: String,
    val name: String,
    val opening_hours: OpeningHours,
    val permanently_closed: Boolean,
    val photos: List<Photo>,
    val place_id: String,
    val plus_code: PlusCode,
    val price_level: Int,
    val rating: Double,
    val reference: String,
    val scope: String,
    val types: List<String>,
    val user_ratings_total: Double,
    val vicinity: String
)