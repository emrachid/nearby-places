package com.example.findnearbyplaces.presentation.di.place

import com.example.findnearbyplaces.presentation.place.PlaceActivity
import dagger.Subcomponent

@PlaceScope
@Subcomponent(modules = [PlaceModule::class])
interface PlaceSubComponent {
    fun inject(placeActivity: PlaceActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PlaceSubComponent
    }
}