package com.example.findnearbyplaces.domain.repository

import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location

interface AddressRepository {
    suspend fun findByLocation(location: Location): Address?
}