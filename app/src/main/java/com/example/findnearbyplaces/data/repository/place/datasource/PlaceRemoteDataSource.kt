package com.example.findnearbyplaces.data.repository.place.datasource

import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.NearByResponse
import retrofit2.Response

interface PlaceRemoteDataSource {
    suspend fun getNearByPlaces(type: String, location: Location): Response<NearByResponse>
}