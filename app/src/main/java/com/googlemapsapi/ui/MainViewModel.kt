package com.googlemapsapi.ui.theme

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import com.googlemapsapi.core.Constants
import com.googlemapsapi.data.GooglePlacesRepository
import com.googlemapsapi.Prediction
import com.googlemapsapi.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val googlePlacesRepository: GooglePlacesRepository,
    val context: GeoApiContext
) : ViewModel() {

    val predictionsResults = MutableStateFlow<List<Prediction>>(emptyList())
    val selectedPrediction: MutableState<Prediction?> = mutableStateOf(null)
    val selectedPredictionLatLng = mutableStateOf(LatLng(Constants.default_LAT, Constants.default_LNG))

    fun selectPrediction(prediction : Prediction){
        selectedPrediction.value = prediction
        getLangLong()
        clearPredictions()
    }
    fun clearSelectedPrediction(){
        selectedPrediction.value = null
    }
    fun clearPredictions() {
        predictionsResults.value = emptyList()
    }
    fun getLangLong() {
        try {
            val results: Array<GeocodingResult> =
                GeocodingApi.geocode(context, selectedPrediction.value?.description).await()
            if (results.isNotEmpty()) {
                val latitude = results[0].geometry.location.lat
                val longitude = results[0].geometry.location.lng
                selectedPredictionLatLng.value = LatLng(latitude, longitude)
            }
            else{
                Log.i("TAG", "else")
            }
        } catch (e: Exception) {
            Log.i("TAG", e.localizedMessage ?: "???")
        }
    }

    fun getPredictions(query: String) {
        viewModelScope.launch {
            val request = googlePlacesRepository.getPredictions(input = query)
            when (request) {
                is Resource.Success -> {
                    predictionsResults.value = request.data?.predictions ?: emptyList()
                }
                is Resource.Error -> {
                    Log.i("TAG", "error")
                }
                else -> {
                    Log.i("TAG", "else")
                }
            }
        }
    }
}