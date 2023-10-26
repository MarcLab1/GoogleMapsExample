package com.googlemapsapi

data class GooglePrediction(
    val predictions: List<Prediction>,
    val status: String
)