package com.example.findnearbyplaces.data.repository.address.datasourceimpl

import android.util.Log
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressCacheDataSource

class AddressCacheDataSourceImpl : AddressCacheDataSource {

    companion object {
        private var TAG = AddressCacheDataSourceImpl::class.java.simpleName

        private const val CACHE_TIMEOUT = 86400L // 1 day in seconds
    }

    private var locationToAddress = mutableMapOf<Location, Address>()
    private var locationCreatedAt = mutableMapOf<Location, Long>()

    private fun isTimeExpired(location: Location): Boolean {
        return if ((System.currentTimeMillis() - locationCreatedAt[location]!!) > (CACHE_TIMEOUT * 1000L)) {
            Log.d(TAG, "TimeExpired")
            true
        } else {
            false
        }
    }

    override suspend fun getAddressFromCache(location: Location): Address? {
        return if (locationCreatedAt[location] == null || isTimeExpired(location)) {
            null
        } else {
            locationToAddress[location]
        }
    }

    override suspend fun saveAddressIntoCache(location: Location, address: Address) {
        locationToAddress[location] = address
        locationCreatedAt[location] = System.currentTimeMillis()
    }
}