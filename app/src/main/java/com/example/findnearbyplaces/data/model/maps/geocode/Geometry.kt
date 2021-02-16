package com.example.findnearbyplaces.data.model.maps.geocode

import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.model.maps.Viewport

data class Geometry(
    val location: Location,
    val location_type: String,
    val viewport: Viewport
)