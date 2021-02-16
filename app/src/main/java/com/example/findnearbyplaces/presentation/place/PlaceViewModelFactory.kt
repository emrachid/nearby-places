package com.example.findnearbyplaces.presentation.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findnearbyplaces.domain.usecase.GetNearByPlaces

class PlaceViewModelFactory(private val getNearByPlaces: GetNearByPlaces): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            return PlaceViewModel(getNearByPlaces) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}