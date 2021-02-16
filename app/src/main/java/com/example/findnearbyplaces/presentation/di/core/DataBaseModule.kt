package com.example.findnearbyplaces.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.example.findnearbyplaces.data.db.StreetAddressDAO
import com.example.findnearbyplaces.data.db.StreetAddressDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAddressDataBase(context: Context): StreetAddressDatabase {
        return Room.databaseBuilder(context, StreetAddressDatabase::class.java, "street-address")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideStreetAddressDAO(streetAddressDatabase: StreetAddressDatabase): StreetAddressDAO {
        return streetAddressDatabase.streetAddressDAO
    }
}