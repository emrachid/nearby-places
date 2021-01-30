package com.example.findnearbyplaces.data.model.place

data class NearByResponse(
    val html_attributions: List<Any>,
    val next_page_token: String,
    val results: List<Place>,
    val status: String
)