package com.example.findnearbyplaces.data.api

import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.Place
import com.example.findnearbyplaces.data.model.maps.Location

interface APIService {
    suspend fun getNearByPlaces(type: String, location: Location): List<Place>?

    suspend fun reverseGeocodingIntoAddress(location: Location): Address?
}