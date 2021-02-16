package com.example.findnearbyplaces.presentation.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.domain.usecase.GetNearByPlaces

class PlaceViewModel(private val getNearByPlaces: GetNearByPlaces): ViewModel() {
    // Live data is executing in Dispatchers.Main context here.
    // However, the context will change to Dispatchers.IO in the data layer.
    // This is the best practice because UI elements can be updated when result is available.
    fun getNearByPlaces(type: String, location: Location) = liveData {
        val places = getNearByPlaces.execute(type, location)
        emit(places)
    }
}