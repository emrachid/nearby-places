package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.ReverseGeocodingService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocationIQApiModule(private val baseUrl: String) {

    @Singleton
    @Provides
    @Named("retrofit_locationIQ")
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideReverseGeocodingService(@Named("retrofit_locationIQ") retrofit: Retrofit): ReverseGeocodingService {
        return retrofit.create(ReverseGeocodingService::class.java)
    }
}