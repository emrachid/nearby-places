package com.example.findnearbyplaces.data.repository.address.datasourceimpl

import com.example.findnearbyplaces.data.db.StreetAddress
import com.example.findnearbyplaces.data.db.StreetAddressDAO
import com.example.findnearbyplaces.data.model.address.Address
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressLocalDataSource

class AddressLocalDataSourceImpl(private val streetAddressDAO: StreetAddressDAO) : AddressLocalDataSource {

    companion object {
        private const val DATA_EXPIRED_TIME = 2592000L // 30 days in seconds
    }

    override suspend fun getAddress(location: Location): Address? {
        var address: Address? = null
        streetAddressDAO.getByLocation(location.lat, location.lng)?.apply {
            if ((System.currentTimeMillis() - this.createdAt) <= (DATA_EXPIRED_TIME * 1000L)) {
                address = this.address
            }
        }
        return address
    }

    override suspend fun saveAddress(location: Location, address: Address) {
        streetAddressDAO.insert(StreetAddress(location, address, System.currentTimeMillis()))
    }
}