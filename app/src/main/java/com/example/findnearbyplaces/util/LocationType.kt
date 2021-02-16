package com.example.findnearbyplaces.util

object LocationType {
    const val BARS = "bar"
    const val CAFES = "cafe"
    const val RESTAURANT = "restaurant"

    fun getLocationIQType(type: String): String {
        return when (type) {
            BARS -> "pub"
            CAFES -> ""
            RESTAURANT -> "restaurant"
            else -> ""
        }
    }
}