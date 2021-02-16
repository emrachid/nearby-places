package com.example.findnearbyplaces.data.db

import androidx.room.Embedded
import androidx.room.Entity
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.maps.Location

@Entity(tableName = "reverse_geocoding", primaryKeys = ["lat", "lng"])
data class StreetAddress (
    @Embedded
    val location: Location,

    @Embedded
    val address: Address,

    val createdAt: Long
)