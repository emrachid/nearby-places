package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.domain.repository.PlaceRepository
import com.example.findnearbyplaces.domain.usecase.GetNearByPlaces
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNearByPlaces(placeRepository: PlaceRepository): GetNearByPlaces {
        return GetNearByPlaces(placeRepository)
    }
}