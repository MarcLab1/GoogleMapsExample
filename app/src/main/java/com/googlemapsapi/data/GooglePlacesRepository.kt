package com.googlemapsapi.data

import com.googlemapsapi.GoogleAddress
import com.googlemapsapi.GooglePrediction
import com.googlemapsapi.core.Resource
import javax.inject.Inject

class GooglePlacesRepository @Inject constructor(
    private val api: GooglePlacesApi,
){
    suspend fun getPredictions(input: String): Resource<GooglePrediction> {
        val response = try {
            api.getPredictions(input = input)
        } catch (e: Exception) {
            return Resource.Error(e)
        }
        return Resource.Success(response)
    }
    suspend fun getAddress(query: String): Resource<GoogleAddress> {
        val response = try {
            api.getAddress(query = query)
        } catch (e: Exception) {
            return Resource.Error(e)
        }
        return Resource.Success(response)
    }
}