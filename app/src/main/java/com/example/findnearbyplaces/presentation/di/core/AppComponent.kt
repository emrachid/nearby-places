package com.example.findnearbyplaces.presentation.di.core

import com.example.findnearbyplaces.presentation.di.home.HomeSubComponent
import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CacheDataModule::class,
    GooglePlacesApiModule::class,
    LocationIQApiModule::class,
    LocationDistanceModule::class,
    LocationIQApiModule::class,
    PlaceRemoteDataModule::class,
    AddressRemoteDataModule::class,
    AddressLocalDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    DataBaseModule::class,
    APIServiceModule::class
])
interface AppComponent {
    fun placeSubComponent(): PlaceSubComponent.Factory
    fun homeSubComponent(): HomeSubComponent.Factory
}