package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    CacheDataModule::class,
    LocationDistanceModule::class,
    RemoteDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface AppComponent {
    fun placeSubComponent(): PlaceSubComponent.Factory
}