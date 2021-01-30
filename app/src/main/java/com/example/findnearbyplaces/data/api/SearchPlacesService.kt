package com.example.findnearbyplaces.data.api

import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.NearByResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchPlacesService {
    @GET("maps/api/place/nearbysearch/json?rankby=distance")
    suspend fun getNearByPlaces(@Query("type") type: String,
                                @Query("location") location: Location,
                                @Query("key") apiKey: String
    ): Response<NearByResponse>
}