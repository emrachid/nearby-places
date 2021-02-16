package com.example.findnearbyplaces.domain.repository

import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.model.Place

interface PlaceRepository {
    suspend fun getNearByPlaces(type: String, location: Location): List<Place>?
}