package com.example.findnearbyplaces.data.repository

import android.util.Log
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.repository.address.datasource.AddressCacheDataSource
import com.example.findnearbyplaces.data.repository.address.datasource.AddressLocalDataSource
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource
import com.example.findnearbyplaces.domain.repository.AddressRepository

class AddressRepositoryImpl(
        private val addressRemoteDataSource: AddressRemoteDataSource,
        private val addressLocalDataSource: AddressLocalDataSource,
        private val addressCacheDataSource: AddressCacheDataSource
) : AddressRepository {

    companion object {
        private var TAG = AddressRepositoryImpl::class.java.simpleName
    }

    override suspend fun findByLocation(location: Location): Address? {
        val address = getAddressFromCache(location)
        Log.d(TAG, "findByLocation: $location - $address")
        return address
    }

    private suspend fun getAddressFromApi(location: Location): Address? {
        val address = addressRemoteDataSource.getAddress(location)
        Log.d(TAG, "getAddressFromApi: $address")
        return address
    }

    private suspend fun getAddressFromLocalDb(location: Location) : Address? {
        val address = addressLocalDataSource.getAddress(location)
        Log.d(TAG, "getAddressFromLocalDb: $address")
        return address
    }

    private suspend fun getAddressFromCache(location: Location): Address? {
        var address = addressCacheDataSource.getAddressFromCache(location)
        if (address == null) {
            address = getAddressFromLocalDb(location)
            address?.let { addressCacheDataSource.saveAddressIntoCache(location, it) }
        }
        if (address == null) {
            address = getAddressFromApi(location)
            address?.let {
                addressLocalDataSource.saveAddress(location, it)
                addressCacheDataSource.saveAddressIntoCache(location, it)
            }
        }
        return address
    }
}