package com.example.findnearbyplaces.data.repository.place.datasource

import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.model.Place

interface PlaceCacheDataSource {
    suspend fun getNearByPlacesFromCache(type: String, location: Location): List<Place>?
    suspend fun saveNearByPlacesIntoCache(type: String, location: Location, places: List<Place>)
}