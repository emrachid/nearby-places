package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.SearchPlacesService
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
import com.example.findnearbyplaces.data.repository.place.datasourceimpl.PlaceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun providePlaceRemoteDataSource(searchPlacesService: SearchPlacesService): PlaceRemoteDataSource {
        return PlaceRemoteDataSourceImpl(searchPlacesService, apiKey)
    }
}