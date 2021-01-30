package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.repository.PlaceRepositoryImpl
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
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
}