package com.example.findnearbyplaces.presentation

import android.app.Application
import com.example.findnearbyplaces.BuildConfig
import com.example.findnearbyplaces.presentation.di.Injector
import com.example.findnearbyplaces.presentation.di.core.*
import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiModule(ApiModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createPlaceSubComponent(): PlaceSubComponent {
        return appComponent.placeSubComponent().create()
    }
}