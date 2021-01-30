package com.example.findnearbyplaces.domain.repository

import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.Place

interface PlaceRepository {
    suspend fun getNearByPlaces(type: String, location: Location): List<Place>?
}