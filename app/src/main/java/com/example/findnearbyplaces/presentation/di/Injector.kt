package com.example.findnearbyplaces.presentation.di

import com.example.findnearbyplaces.presentation.di.home.HomeSubComponent
import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent

interface Injector {
    fun createPlaceSubComponent(): PlaceSubComponent
    fun createHomeSubComponent(): HomeSubComponent
}