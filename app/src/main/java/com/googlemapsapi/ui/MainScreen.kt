package com.googlemapsapi.ui.theme

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.googlemapsapi.core.Constants
import com.googlemapsapi.Prediction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val predictionResults = vm.predictionsResults.collectAsState()
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(vm.selectedPredictionLatLng.value, Constants.default_ZOOM)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box {
        GoogleMap(
            cameraPositionState = cameraPositionState
        ) {
            MarkerInfoWindowContent(
                state = MarkerState(
                    position = vm.selectedPredictionLatLng.value
                )
            ) { marker ->
                Text(marker.title ?: "selection", color = Color.Red)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 4.dp, end = 4.dp)
        ) {

            OutlinedTextField(
                value = query,
                onValueChange = { newValue ->
                    vm.getPredictions(newValue)
                    query = newValue
                    expanded = true
                },
                maxLines = 1,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            vm.clearSelectedPrediction()
                            query = ""
                            expanded = false
                        })
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()}),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            )
            if (expanded) {
                PredictionResults(
                    results = predictionResults.value,
                    selectPrediction = {
                        vm.selectPrediction(it)
                        query = it.description
                        expanded = false
                        keyboardController?.hide()
                    }
                )
            } /*else if (vm.selectedPrediction.value != null) {
                Text(text = "Place id = ${vm.selectedPrediction.value?.place_id}")
                Text(text = "${vm.selectedPrediction.value?.structured_formatting?.secondary_text}")
                Text(text = "Latitude = ${vm.lngLng.value?.latitude}")
                Text(text = "Longitude = ${vm.lngLng.value?.longitude}")
            }*/
        }
    }
    LaunchedEffect(vm.selectedPredictionLatLng.value) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(vm.selectedPredictionLatLng.value, Constants.default_ZOOM, 0f, 0f)
            ),
            durationMs = 1000
        )
    }
}

@Composable
fun PredictionResults(
    results: List<Prediction>,
    selectPrediction: (Prediction) -> Unit
) {
    LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        items(results) { result ->
            PredictionItem(result, { selectPrediction(it) })
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun PredictionItem(
    result: Prediction,
    selectPrediction: (Prediction) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { selectPrediction(result) })
    {
        Text(
            text = result.description,
        )
    }

}