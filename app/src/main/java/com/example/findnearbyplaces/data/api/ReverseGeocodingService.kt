package com.example.findnearbyplaces.data.api

import com.example.findnearbyplaces.data.model.address.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeocodingService {
    @GET("reverse.php?format=json")
    suspend fun getAddress(@Query("lat") latitude: String,
                           @Query("lon") longitude: String,
                           @Query("key") apiKey: String
    ): Response<AddressResponse>
}