package com.example.findnearbyplaces.data.model.maps.place

import com.example.findnearbyplaces.data.model.maps.Location
import com.example.findnearbyplaces.data.model.maps.Viewport

data class Geometry(
    val location: Location,
    val viewport: Viewport
)