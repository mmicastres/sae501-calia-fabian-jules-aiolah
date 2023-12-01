package com.example.castresautresor

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.osmdroid.util.GeoPoint

lateinit var fusedLocationProviderClient: FusedLocationProviderClient
var lon by mutableStateOf<Double?>(null)
var lat by mutableStateOf<Double?>(null)

var currentPos by mutableStateOf(Position(0.0, 0.0))
var currentGeoPoint by mutableStateOf(GeoPoint(0.0,0.0))

val locationCallback = object : LocationCallback() {
    override fun onLocationResult(locationResult: LocationResult) {
        val lastLocation = locationResult.lastLocation
        lon = lastLocation?.longitude
        lat = lastLocation?.latitude

        if(lat != null){
        currentPos.latitude = lat!!
        currentPos.longitude = lon!!

        currentGeoPoint.latitude = lat!!
        currentGeoPoint.longitude = lon!!}


    }
}


// Location management

@Composable
fun DisplayLocation() {

    if(lat != null && lon != null) {
        var newLoc = Position(lat!!, lon!!)
        Log.e("position",newLoc.toString())

    }
}

@Composable
fun DisplayThermo(point : Position, point2 : Position){
        val distance_point = point.getDistance(point2).toInt()
}


/*

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton

import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun map() {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = rememberCameraPositionState()
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}
 */