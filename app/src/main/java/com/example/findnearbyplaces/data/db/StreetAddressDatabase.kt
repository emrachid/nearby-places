package com.example.findnearbyplaces.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StreetAddress::class], version = 2, exportSchema = false)
abstract class StreetAddressDatabase: RoomDatabase() {
    abstract val streetAddressDAO: StreetAddressDAO
}

