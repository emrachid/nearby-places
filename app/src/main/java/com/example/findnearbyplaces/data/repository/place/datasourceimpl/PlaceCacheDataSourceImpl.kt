package com.example.findnearbyplaces.data.repository.place.datasourceimpl

import android.util.Log
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.Place
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.util.LocationDistance

class PlaceCacheDataSourceImpl(private val locationDistance: LocationDistance) :
    PlaceCacheDataSource {

    companion object {
        private var TAG = PlaceCacheDataSourceImpl::class.java.simpleName

        private const val CACHE_DISABLED = 0
        private const val LOCATION_UPDATE = 1
        private const val TIME_EXPIRED = 2
        private const val TIME_AND_LOCATION = 3
        private const val DISTANCE_THRESHOLD = 0.04 // in miles
        private const val TIMEOUT = 60 // in seconds
        private const val CACHE_STRATEGY = TIME_EXPIRED
    }

    private var cacheCreatedAt = 0L
    private var lastLocation = mutableMapOf<String, Location>()
    private var lastPlaces = mutableMapOf<String, List<Place>>()

    private fun isTheSameLocation(type: String, location: Location): Boolean {
        val result = locationDistance.isNearBy(lastLocation[type]!!, location, DISTANCE_THRESHOLD)
        Log.d(TAG, "isLocationChange: $result")
        return result
    }

    private fun isTimeExpired(): Boolean {
        val result = (System.currentTimeMillis() - cacheCreatedAt) > (TIMEOUT * 1000)
        Log.d(TAG, "isTimeExpired: $result")
        return result
    }

    private fun isCacheValid(type: String, location: Location): Boolean {
        Log.d(TAG, "isCacheValid: $type - $location")

        if (lastLocation[type] == null) {
            return false
        }

        return when (CACHE_STRATEGY) {
            CACHE_DISABLED -> false
            LOCATION_UPDATE -> isTheSameLocation(type, location)
            TIME_EXPIRED -> !isTimeExpired()
            TIME_AND_LOCATION -> !isTimeExpired() && isTheSameLocation(type, location)
            else -> false
        }
    }

    override suspend fun getNearByPlacesFromCache(type: String, location: Location): List<Place>? {
        return if (isCacheValid(type, location)) {
            lastPlaces[type]
        } else {
            null
        }
    }

    override suspend fun saveNearByPlacesIntoCache(type: String, location: Location, places: List<Place>) {
        lastLocation[type] = location
        lastPlaces[type] = places
        cacheCreatedAt = System.currentTimeMillis()
    }
}