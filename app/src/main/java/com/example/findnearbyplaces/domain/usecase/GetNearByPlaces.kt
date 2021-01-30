package com.example.findnearbyplaces.domain.usecase

import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.Place
import com.example.findnearbyplaces.domain.repository.PlaceRepository

class GetNearByPlaces(private val placeRepository: PlaceRepository) {
    suspend fun execute(type: String, location: Location): List<Place>? {
        return placeRepository.getNearByPlaces(type, location)
    }
}