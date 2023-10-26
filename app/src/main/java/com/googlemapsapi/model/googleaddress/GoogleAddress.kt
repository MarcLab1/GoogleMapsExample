package com.googlemapsapi

data class GoogleAddress(
    val html_attributions: List<Any>,
    val results: List<Result>,
    val status: String
)