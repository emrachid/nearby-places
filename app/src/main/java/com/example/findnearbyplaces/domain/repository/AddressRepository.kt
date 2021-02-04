package com.example.findnearbyplaces.domain.repository

import com.example.findnearbyplaces.data.model.address.Address
import com.example.findnearbyplaces.data.model.place.Location

interface AddressRepository {
    suspend fun findByLocation(location: Location): Address?
}