package com.example.findnearbyplaces.data.repository.address.datasource

import com.example.findnearbyplaces.data.model.address.AddressResponse
import com.example.findnearbyplaces.data.model.place.Location
import retrofit2.Response

interface AddressRemoteDataSource {
    suspend fun getAddress(location: Location): Response<AddressResponse>
}