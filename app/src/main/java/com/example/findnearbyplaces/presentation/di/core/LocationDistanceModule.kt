package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.util.LocationDistance
import com.example.findnearbyplaces.util.LocationDistanceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationDistanceModule {

    @Singleton
    @Provides
    fun provideLocationDistance(): LocationDistance {
        return LocationDistanceImpl()
    }
}