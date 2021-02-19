package com.example.findnearbyplaces.data.repository.address.datasourceimpl

import com.example.findnearbyplaces.data.db.StreetAddress
import com.example.findnearbyplaces.data.db.StreetAddressDAO
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressLocalDataSourceImpl(private val streetAddressDAO: StreetAddressDAO) : AddressLocalDataSource {

    companion object {
        private const val DATA_EXPIRED_TIME = 2592000L // 30 days in seconds
    }

    override suspend fun getAddress(location: Location): Address? {
        var address: Address? = null

        withContext(Dispatchers.IO) {
            streetAddressDAO.getByLocation(location.lat, location.lng)?.apply {
                if ((System.currentTimeMillis() - this.createdAt) <= (DATA_EXPIRED_TIME * 1000L)) {
                    address = this.address
                }
            }
        }

        return address
    }

    override suspend fun saveAddress(location: Location, address: Address) {
        withContext(Dispatchers.IO) {
            streetAddressDAO.insert(StreetAddress(location, address, System.currentTimeMillis()))
        }
    }
}