package com.googlemapsapi.data

import com.googlemapsapi.core.Constants
import com.googlemapsapi.GoogleAddress
import com.googlemapsapi.GooglePrediction
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("maps/api/place/autocomplete/json")
    suspend fun getPredictions(
        @Query("key") key: String = Constants.GOOGLE_MAPS_API_KEY,
        @Query("types") types: String = "address",
        @Query("location") location : String = "${Constants.default_LAT}%2C${Constants.default_LNG}", //&location=37.76999%2C-122.44696
        @Query("radius") radius : String = Constants.default_RADIUS,
        @Query("input") input: String
    ): GooglePrediction

    @GET("maps/api/place/textsearch/json")
    suspend fun getAddress(
        @Query("key") key: String = Constants.GOOGLE_MAPS_API_KEY,
        @Query("query") query: String,
    ) : GoogleAddress

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
    }
}