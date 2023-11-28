package com.example.castresautresor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var lon by mutableStateOf<Double?>(null)
    private var lat by mutableStateOf<Double?>(null)
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation = locationResult.lastLocation
            lon = lastLocation?.longitude
            lat = lastLocation?.latitude
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            var pos1 = Position(44.3455, 2.5936)
            val pos2 = Position(43.9202, 2.1487)
            val angle = pos1.getAngle(pos2)
            Log.e("distance", angle.toString())
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { toggleLocationUpdates() }) {
                    Text(text = if (lon != null && lat != null) "Stop Location Updates" else "Start Location Updates")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (lon != null && lat != null) {
                    map()
                }
            }
        }
    }

    @Composable
    fun DisplayLocation(title: String) {
        Column {
            Text(text = "$title:")
            Text(text = "Latitude: ${lat ?: 0.0}, Longitude: ${lon ?: 0.0}")
        }
        if(lat != null && lon != null) {
            var newLoc = Position(lat!!, lon!!)
            Log.e("position",newLoc.toString())

        }
    }

    private fun toggleLocationUpdates() {
        lifecycleScope.launch {
            if (lon != null && lat != null) {
                stopLocationUpdates()
            } else {
                // Vérifiez d'abord l'autorisation
                if (checkLocationPermission()) {
                    startLocationUpdates()
                } else {
                    // Si l'autorisation n'est pas accordée, demandez-la
                    requestLocationPermission()
                }
            }
        }
    }

    private fun startLocationUpdates() {
        lifecycleScope.launch {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).apply {
                setMinUpdateDistanceMeters(1F)
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            try {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    null
                )
            } catch (e: SecurityException) {
                // Gérer l'exception de sécurité ici
            }
        }
    }

    private fun stopLocationUpdates() {
        lifecycleScope.launch {
            try {
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            } catch (e: SecurityException) {
                // Gérer l'exception de sécurité ici
            }
            lon = null
            lat = null
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 101
    }
}
