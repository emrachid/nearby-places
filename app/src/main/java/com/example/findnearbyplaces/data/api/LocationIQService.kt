package com.example.findnearbyplaces.data.api

import com.example.findnearbyplaces.data.model.locationiq.AddressResponse
import com.example.findnearbyplaces.data.model.locationiq.PlaceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationIQService {
    @GET("nearby.php?radius=10000&format=json")
    suspend fun getNearByPlaces(@Query("tag") type: String,
                                @Query("lat") latitude: String,
                                @Query("lon") longitude: String,
                                @Query("key") apiKey: String
    ): Response<PlaceResponse>

    @GET("reverse.php?format=json")
    suspend fun reverseGeocodingIntoAddress(@Query("lat") latitude: String,
                                            @Query("lon") longitude: String,
                                            @Query("key") apiKey: String
    ): Response<AddressResponse>
}