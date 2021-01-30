package com.example.findnearbyplaces.data.repository.place.datasourceimpl

import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.Place
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.util.LocationDistance

class PlaceCacheDataSourceImpl(private val locationDistance: LocationDistance) :
    PlaceCacheDataSource {
    private var lastLocation = mutableMapOf<String, Location>()
    private var lastPlaces = mutableMapOf<String, List<Place>>()

    override suspend fun getNearByPlacesFromCache(type: String, location: Location): List<Place>? {
        lastLocation[type]?.let {
            if (locationDistance.isNearBy(it, location)) {
                return lastPlaces[type]
            }
        }

        return null
    }

    override suspend fun saveNearByPlacesIntoCache(type: String, location: Location, places: List<Place>) {
        lastLocation[type] = location
        lastPlaces[type] = places
    }
}