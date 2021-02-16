package com.example.findnearbyplaces.data.repository.place.datasourceimpl

import com.example.findnearbyplaces.data.api.APIService
import com.example.findnearbyplaces.data.model.Place
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource

class PlaceRemoteDataSourceImpl(
    private val apiService: APIService
) : PlaceRemoteDataSource {
    override suspend fun getNearByPlaces(type: String, location: Location): List<Place>? {
        return apiService.getNearByPlaces(type, location)
    }
}