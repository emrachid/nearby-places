package com.example.findnearbyplaces.presentation.di.core

import android.content.Context
import com.example.findnearbyplaces.presentation.di.place.PlaceSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [PlaceSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}