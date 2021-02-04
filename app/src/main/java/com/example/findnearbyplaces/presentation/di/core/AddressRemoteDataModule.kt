package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.ReverseGeocodingService
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource
import com.example.findnearbyplaces.data.repository.address.datasourceimpl.AddressRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddressRemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideAddressRemoteDataSource(reverseGeocodingService: ReverseGeocodingService): AddressRemoteDataSource {
        return AddressRemoteDataSourceImpl(reverseGeocodingService, apiKey)
    }
}