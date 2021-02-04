package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.repository.AddressRepositoryImpl
import com.example.findnearbyplaces.data.repository.PlaceRepositoryImpl
import com.example.findnearbyplaces.data.repository.address.datasource.AddressCacheDataSource
import com.example.findnearbyplaces.data.repository.address.datasource.AddressLocalDataSource
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
import com.example.findnearbyplaces.domain.repository.AddressRepository
import com.example.findnearbyplaces.domain.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providePlaceRepository(
        placeRemoteDataSource: PlaceRemoteDataSource,
        placeCacheDataSource: PlaceCacheDataSource
    ): PlaceRepository {
        return PlaceRepositoryImpl(placeRemoteDataSource, placeCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideAddressRepository(
        addressRemoteDataSource: AddressRemoteDataSource,
        addressLocalDataSource: AddressLocalDataSource,
        addressCacheDataSource: AddressCacheDataSource
    ): AddressRepository {
        return AddressRepositoryImpl(addressRemoteDataSource, addressLocalDataSource, addressCacheDataSource)
    }
}