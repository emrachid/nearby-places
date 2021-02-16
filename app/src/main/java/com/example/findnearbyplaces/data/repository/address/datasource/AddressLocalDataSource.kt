package com.example.findnearbyplaces.data.repository.address.datasource

import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location

interface AddressLocalDataSource {
    suspend fun getAddress(location: Location): Address?
    suspend fun saveAddress(location: Location, address: Address)
}