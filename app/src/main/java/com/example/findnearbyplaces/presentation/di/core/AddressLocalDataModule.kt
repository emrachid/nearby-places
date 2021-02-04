package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.db.StreetAddressDAO
import com.example.findnearbyplaces.data.repository.address.datasource.AddressLocalDataSource
import com.example.findnearbyplaces.data.repository.address.datasourceimpl.AddressLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddressLocalDataModule {

    @Singleton
    @Provides
    fun provideAddressLocalDataSource(streetAddressDAO: StreetAddressDAO): AddressLocalDataSource {
        return AddressLocalDataSourceImpl(streetAddressDAO)
    }
}