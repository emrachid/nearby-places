package com.example.findnearbyplaces.data.repository.address.datasourceimpl

import com.example.findnearbyplaces.data.api.ReverseGeocodingService
import com.example.findnearbyplaces.data.model.address.AddressResponse
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource
import retrofit2.Response

class AddressRemoteDataSourceImpl(
        private val reverseGeocoding: ReverseGeocodingService,
        private val apiKey: String
) : AddressRemoteDataSource {
    override suspend fun getAddress(location: Location): Response<AddressResponse> {
        return reverseGeocoding.getAddress(location.lat.toString(), location.lng.toString(), apiKey)
    }
}