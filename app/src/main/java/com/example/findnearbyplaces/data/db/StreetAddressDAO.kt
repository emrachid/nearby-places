package com.example.findnearbyplaces.data.db

import androidx.room.*

@Dao
interface StreetAddressDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(streetAddress: StreetAddress)

    @Update
    suspend fun update(streetAddress: StreetAddress)

    @Delete
    suspend fun delete(streetAddress: StreetAddress)

    @Query("DELETE FROM reverse_geocoding")
    suspend fun deleteAll()

    @Query("SELECT * FROM reverse_geocoding WHERE lat=:lat and lng=:lng")
    suspend fun getByLocation(lat: Double, lng: Double): StreetAddress?
}