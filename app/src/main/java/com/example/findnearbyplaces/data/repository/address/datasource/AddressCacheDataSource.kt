package com.example.findnearbyplaces.data.repository.address.datasource

import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location

interface AddressCacheDataSource {
    suspend fun getAddressFromCache(location: Location): Address?
    suspend fun saveAddressIntoCache(location: Location, address: Address)
}