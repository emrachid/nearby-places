package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.APIService
import com.example.findnearbyplaces.data.api.LocationIQService
import com.example.findnearbyplaces.data.repository.address.datasource.AddressRemoteDataSource
import com.example.findnearbyplaces.data.repository.address.datasourceimpl.AddressRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddressRemoteDataModule {

    @Singleton
    @Provides
    fun provideAddressRemoteDataSource(apiService: APIService): AddressRemoteDataSource {
        return AddressRemoteDataSourceImpl(apiService)
    }
}