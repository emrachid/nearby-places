package com.example.findnearbyplaces.data.api

import android.util.Log
import com.example.findnearbyplaces.BuildConfig
import com.example.findnearbyplaces.data.model.Address
import com.example.findnearbyplaces.data.model.Place
import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.util.LocationDistance
import com.example.findnearbyplaces.util.LocationType
import kotlin.random.Random

class APIServiceImpl(private val googleMapsAPIService: GoogleMapsAPIService,
                     private val locationIQService: LocationIQService,
                     private val locationDistance: LocationDistance
) : APIService {

    companion object {
        var TAG = APIServiceImpl::class.simpleName
    }

    override suspend fun getNearByPlaces(type: String, location: Location): List<Place>? {
        val placesByMapsAPI = getNearByPlacesMapsAPI(type, location)
        val placesByLocationIQ = getNearByPlacesLocationIQ(type, location)

        // In case of network error, both APIs will fail.
        // The activity will handle it and show an error message.
        if (placesByMapsAPI == null && placesByLocationIQ ==  null) {
            Log.d(TAG, "Both APIs failed!!!")
            return null
        }

        if (placesByMapsAPI ==  null) {
            Log.d(TAG, "Places by LocationIQ: $placesByLocationIQ")
            return placesByLocationIQ
        }

        if (placesByLocationIQ == null) {
            Log.d(TAG, "Places by MapsAPI: $placesByMapsAPI")
            return placesByMapsAPI
        }

        // Both APIs found places. Need to merge the result
        val placesHashMap = HashMap<String, Place>()
        placesByMapsAPI.forEach { placesHashMap[it.name] = it }
        placesByLocationIQ.forEach { placesHashMap[it.name] = it }

        Log.d(TAG, "Places by MapsAPI: ${placesByMapsAPI.size}")
        Log.d(TAG, "Places by LocationIQ: ${placesByLocationIQ.size}")
        Log.d(TAG, "Merging places: ${placesHashMap.values.toList().size}")

        return placesHashMap.values.toList().sortedBy { it.distance }
    }

    override suspend fun reverseGeocodingIntoAddress(location: Location): Address? {
        var address: Address? = null
        // If both APIs are configured, split the load between them.
        if (BuildConfig.GOOGLE_API_KEY.isNotBlank() && BuildConfig.LOCATIONIQ_API_KEY.isNotBlank()) {
            if (Random.nextInt(0, 2) == 0) {
                address = getAddressUsingMapsAPI(location)
                Log.d(TAG, "Address by MapsAPI: $address")
                if (address == null) {
                    address = getAddressUsingLocationIQ(location)
                    Log.d(TAG, "Address by LocationIQ: $address")
                }
            } else {
                address = getAddressUsingLocationIQ(location)
                Log.d(TAG, "Address by LocationIQ: $address")
                if (address == null) {
                    address = getAddressUsingMapsAPI(location)
                    Log.d(TAG, "Address by MapsAPI: $address")
                }
            }
        } else if (BuildConfig.GOOGLE_API_KEY.isNotBlank()) {
            address = getAddressUsingMapsAPI(location)
            Log.d(TAG, "Address by MapsAPI: $address")
        } else if (BuildConfig.LOCATIONIQ_API_KEY.isNotBlank()) {
            address = getAddressUsingLocationIQ(location)
            Log.d(TAG, "Address by LocationIQ: $address")
        }

        return address
    }

    private suspend fun getAddressUsingMapsAPI(location: Location): Address? {
        var address: Address? = null
        try {
            val response = googleMapsAPIService.reverseGeocodingIntoAddress(location, BuildConfig.GOOGLE_API_KEY)
            if (response.isSuccessful && response.body()?.status == "OK") {
                response.body()?.results?.get(0)?.address_components?.forEach {
                    if (it.types.contains("route")) {
                        address = Address(it.short_name)
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return address
    }

    private suspend fun getAddressUsingLocationIQ(location: Location): Address? {
        var address: Address? = null
        try {
            val response = locationIQService.reverseGeocodingIntoAddress(location.lat.toString(),
                location.lng.toString(), BuildConfig.LOCATIONIQ_API_KEY)

            if (response.isSuccessful) {
                response.body()?.address?.road?.let { address = Address(it) }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return address
    }

    private suspend fun getNearByPlacesMapsAPI(type: String, location: Location): List<Place>? {
        var places: List<Place>? = null
        try {
            if (BuildConfig.GOOGLE_API_KEY.isNotBlank()) {
                val response =
                    googleMapsAPIService.getNearByPlaces(type, location, BuildConfig.GOOGLE_API_KEY)
                if (response.isSuccessful && response.body()?.status == "OK") {
                    places = mutableListOf()
                    response.body()?.results?.forEach {
                        val place = Place(
                            it.business_status, it.name, it.photos,
                            locationDistance.getInMeters(location, it.geometry.location)
                        )
                        places.add(place)
                        Log.d(TAG, "MapsAPI: place = $place")
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return places
    }

    private suspend fun getNearByPlacesLocationIQ(type: String, location: Location): List<Place>? {
        var places: List<Place>? = null
        try {
            if (BuildConfig.LOCATIONIQ_API_KEY.isNotBlank()) {
                val response = locationIQService.getNearByPlaces(LocationType.getLocationIQType(type),
                    location.lat.toString(), location.lng.toString(), BuildConfig.LOCATIONIQ_API_KEY)

                if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                    places = mutableListOf()
                    response.body()?.forEach {
                        if (it.name?.isNotBlank() == true) {
                            val place = Place("", it.name, null, it.distance.toDouble())
                            places.add(place)
                            Log.d(TAG, "LocationIQ: place = $place")
                        }
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return places
    }
}