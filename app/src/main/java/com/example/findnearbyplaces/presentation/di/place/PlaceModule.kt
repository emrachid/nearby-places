package com.example.findnearbyplaces.presentation.di.place

import com.example.findnearbyplaces.domain.usecase.GetNearByPlaces
import com.example.findnearbyplaces.presentation.place.PlaceViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceModule {

    @PlaceScope
    @Provides
    fun providePlaceViewModelFactory(getNearByPlaces: GetNearByPlaces): PlaceViewModelFactory {
        return PlaceViewModelFactory(getNearByPlaces)
    }
}