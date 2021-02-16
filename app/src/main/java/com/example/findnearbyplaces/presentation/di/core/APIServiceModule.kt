package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.APIService
import com.example.findnearbyplaces.data.api.APIServiceImpl
import com.example.findnearbyplaces.data.api.GoogleMapsAPIService
import com.example.findnearbyplaces.data.api.LocationIQService
import com.example.findnearbyplaces.util.LocationDistance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class APIServiceModule {

    @Singleton
    @Provides
    fun provideAPIService(googleMapsAPIService: GoogleMapsAPIService,
                          locationIQService: LocationIQService,
                          locationDistance: LocationDistance
    ): APIService {
        return APIServiceImpl(googleMapsAPIService, locationIQService, locationDistance)
    }
}