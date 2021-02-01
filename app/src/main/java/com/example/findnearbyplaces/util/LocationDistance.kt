package com.example.findnearbyplaces.util

import com.example.findnearbyplaces.data.model.place.Location

interface LocationDistance {
    fun isNearBy(fromLocation: Location, toLocation: Location, threshold: Double): Boolean
}