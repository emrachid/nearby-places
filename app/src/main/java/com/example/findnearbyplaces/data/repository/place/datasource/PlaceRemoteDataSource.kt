package com.example.findnearbyplaces.data.repository.place.datasource

import com.example.findnearbyplaces.data.model.Place
import com.example.findnearbyplaces.data.model.maps.Location

interface PlaceRemoteDataSource {
    suspend fun getNearByPlaces(type: String, location: Location): List<Place>?
}