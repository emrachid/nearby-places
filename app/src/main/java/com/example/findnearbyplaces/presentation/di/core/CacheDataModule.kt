package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.data.repository.place.datasourceimpl.PlaceCacheDataSourceImpl
import com.example.findnearbyplaces.util.LocationDistance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun providePlaceCacheDataSource(locationDistance: LocationDistance): PlaceCacheDataSource {
        return PlaceCacheDataSourceImpl(locationDistance)
    }
}