package com.example.findnearbyplaces.data.model.maps

data class Location(
    val lat: Double,
    val lng: Double
) {
    companion object {
        fun parse(location: String): Location {
            val parseResult = location.split(',')
            return Location(parseResult[0].toDouble(), parseResult[1].toDouble())
        }
    }

    override fun toString() = "$lat,$lng"
}