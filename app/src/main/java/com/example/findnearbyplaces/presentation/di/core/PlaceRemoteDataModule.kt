package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.APIService
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
import com.example.findnearbyplaces.data.repository.place.datasourceimpl.PlaceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlaceRemoteDataModule {

    @Singleton
    @Provides
    fun providePlaceRemoteDataSource(apiService: APIService): PlaceRemoteDataSource {
        return PlaceRemoteDataSourceImpl(apiService)
    }
}