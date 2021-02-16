package com.example.findnearbyplaces.util

import com.example.findnearbyplaces.data.model.maps.Location

interface LocationDistance {
    fun isNearBy(fromLocation: Location, toLocation: Location, threshold: Double): Boolean
    fun getInMeters(fromLocation: Location, toLocation: Location): Double
}