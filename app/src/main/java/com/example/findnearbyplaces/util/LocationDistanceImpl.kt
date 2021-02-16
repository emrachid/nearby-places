package com.example.findnearbyplaces.util

import com.example.findnearbyplaces.data.model.maps.Location

class LocationDistanceImpl : LocationDistance {
    companion object {
        private const val MILES_TO_METERS = 1609.344
    }
    override fun isNearBy(fromLocation: Location, toLocation: Location, threshold: Double): Boolean {
        return distance(fromLocation.lat, toLocation.lat, fromLocation.lng, toLocation.lng) < threshold
    }

    override fun getInMeters(fromLocation: Location, toLocation: Location): Double {
        return distance(fromLocation.lat, toLocation.lat, fromLocation.lng, toLocation.lng) * MILES_TO_METERS
    }

    private fun distance(
        lat1: Double,
        lat2: Double,
        lon1: Double,
        lon2: Double
    ): Double {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        var lat1 = lat1
        var lat2 = lat2
        var lon1 = lon1
        var lon2 = lon2
        lon1 = Math.toRadians(lon1)
        lon2 = Math.toRadians(lon2)
        lat1 = Math.toRadians(lat1)
        lat2 = Math.toRadians(lat2)

        // Haversine formula
        val dlon = lon2 - lon1
        val dlat = lat2 - lat1
        val a = (Math.pow(Math.sin(dlat / 2), 2.0)
                + (Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2.0)))
        val c = 2 * Math.asin(Math.sqrt(a))

        // Radius of earth in kilometers. Use 3956
        // for miles
        val r = 6371.0

        // calculate the result
        return c * r
    }
}