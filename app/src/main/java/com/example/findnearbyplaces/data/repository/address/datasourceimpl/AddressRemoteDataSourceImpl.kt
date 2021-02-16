package com.example.findnearbyplaces.data.repository.address.datasourceimpl

import com.example.findnearbyplaces.data.api.APIService
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource

class AddressRemoteDataSourceImpl(
    private val apiService: APIService,
) : AddressRemoteDataSource {
    override suspend fun getAddress(location: Location): Address? {
        return apiService.reverseGeocodingIntoAddress(location)
    }
}