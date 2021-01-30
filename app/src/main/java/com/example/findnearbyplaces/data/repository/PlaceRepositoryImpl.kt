package com.example.findnearbyplaces.data.repository

import android.util.Log
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.data.model.place.Place
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceCacheDataSource
import com.example.findnearbyplaces.data.repository.place.datasource.PlaceRemoteDataSource
import com.example.findnearbyplaces.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val placeCacheDataSource: PlaceCacheDataSource
) : PlaceRepository {

    companion object {
        private var TAG = PlaceRepositoryImpl::class.java.simpleName
    }

    override suspend fun getNearByPlaces(type: String, location: Location): List<Place>? {
        val places = getNearByPlacesFromCache(type, location)
        Log.d(TAG, "getNearByPlaces: $type - $places")
        return places
    }

    private suspend fun getNearByPlacesFromApi(type: String, location: Location): List<Place>? {
        val response = placeRemoteDataSource.getNearByPlaces(type, location)
        Log.d(TAG, "getNearByPlacesFromApi: $response")
        return response.body()?.results
    }

    private suspend fun getNearByPlacesFromCache(type: String, location: Location): List<Place>? {
        var places = placeCacheDataSource.getNearByPlacesFromCache(type, location)
        if (places == null) {
            places = getNearByPlacesFromApi(type, location)
            places?.let { placeCacheDataSource.saveNearByPlacesIntoCache(type, location, it) }
        }
        return places
    }
}