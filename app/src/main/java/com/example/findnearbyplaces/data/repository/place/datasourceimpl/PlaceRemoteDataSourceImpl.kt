package com.example.findnearbyplaces.data.repository.place.datasourceimpl

import com.example.findnearbyplaces.data.api.SearchPlacesService
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.NearByResponse
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
import retrofit2.Response

class PlaceRemoteDataSourceImpl(
    private val searchPlacesService: SearchPlacesService,
    private val apiKey: String
) : PlaceRemoteDataSource {
    override suspend fun getNearByPlaces(type: String, location: Location): Response<NearByResponse> {
        return searchPlacesService.getNearByPlaces(type, location, apiKey)
    }
}