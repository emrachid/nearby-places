package com.example.findnearbyplaces.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findnearbyplaces.domain.usecase.GetAddress

class HomeViewModelFactory(private val getAddress: GetAddress): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getAddress) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}