package com.example.findnearbyplaces.presentation

import android.app.Application
import com.example.findnearbyplaces.BuildConfig
import com.example.findnearbyplaces.presentation.di.Injector
import com.example.findnearbyplaces.presentation.di.core.*
import com.example.findnearbyplaces.presentation.di.home.HomeSubComponent
import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .googlePlacesApiModule(GooglePlacesApiModule(BuildConfig.GOOGLE_BASE_URL))
            .locationIQApiModule(LocationIQApiModule(BuildConfig.LOCATIONIQ_BASE_URL))
            .placeRemoteDataModule(PlaceRemoteDataModule(BuildConfig.GOOGLE_API_KEY))
            .addressRemoteDataModule(AddressRemoteDataModule(BuildConfig.LOCATIONIQ_API_KEY))
            .build()
    }

    override fun createPlaceSubComponent(): PlaceSubComponent {
        return appComponent.placeSubComponent().create()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }
}