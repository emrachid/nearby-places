package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.data.api.GoogleMapsAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GooglePlacesApiModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSearchPlacesService(retrofit: Retrofit): GoogleMapsAPIService {
        return retrofit.create(GoogleMapsAPIService::class.java)
    }
}