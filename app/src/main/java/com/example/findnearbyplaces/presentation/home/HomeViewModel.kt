package com.example.findnearbyplaces.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.findnearbyplaces.data.model.place.Location
import com.example.findnearbyplaces.domain.usecase.GetAddress

class HomeViewModel(private val getAddress: GetAddress): ViewModel() {
    // Live data is executing in Dispatchers.Main context here.
    // However, the context will change to Dispatchers.IO in the data layer.
    // This is the best practice because UI elements can be updated when result is available.
    fun getLocationAddress(location: Location) = liveData {
        val address = getAddress.execute(location)
        emit(address)
    }
}