package com.example.findnearbyplaces.domain.usecase

import com.example.findnearbyplaces.data.model.address.Address
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.domain.repository.AddressRepository

class GetAddress(private val addressRepository: AddressRepository) {
    suspend fun execute(location: Location): Address? {
        return addressRepository.findByLocation(location)
    }
}