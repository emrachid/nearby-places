package com.example.findnearbyplaces.data.api

import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.model.maps.geocode.ReverseGeocodingResponse
import com.example.findnearbyplaces.data.model.maps.place.NearByResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapsAPIService {
    @GET("place/nearbysearch/json?rankby=distance")
    suspend fun getNearByPlaces(@Query("type") type: String,
                                @Query("location") location: Location,
                                @Query("key") apiKey: String
    ): Response<NearByResponse>

    @GET("geocode/json?result_type=street_address")
    suspend fun reverseGeocodingIntoAddress(@Query("latlng") location: Location,
                                            @Query("key") apiKey: String
    ): Response<ReverseGeocodingResponse>
}