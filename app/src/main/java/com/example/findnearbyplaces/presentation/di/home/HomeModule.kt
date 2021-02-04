package com.example.findnearbyplaces.presentation.di.home

import com.example.findnearbyplaces.domain.usecase.GetAddress
import com.example.findnearbyplaces.presentation.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(getAddress: GetAddress): HomeViewModelFactory {
        return HomeViewModelFactory(getAddress)
    }
}