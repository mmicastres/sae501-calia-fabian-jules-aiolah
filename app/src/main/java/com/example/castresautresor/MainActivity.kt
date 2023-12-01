package com.example.castresautresor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import org.osmdroid.views.MapView
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {


    lateinit var permissionContract: ActivityResultLauncher<Array<String>>
    lateinit var homeScreenMapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        Configuration.getInstance().userAgentValue = packageName
        homeScreenMapView = MapView(baseContext)

        homeScreenMapView.controller.setCenter(currentGeoPoint)
        setContent {

           // Create the reference point from which we calculate distance
            val pos2 = Position(43.6223, 2.2589)
            Log.e("lat" , currentGeoPoint.latitude.toString())

           val angle = currentPos.getAngle(pos2)
           Log.e("distance", angle.toString())
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (currentPos.getDistance(pos2).toInt() > 99) {
                    homeScreenMapView.controller.setCenter(currentGeoPoint)

                    startLocationUpdates()

                    Compass(point = currentPos, point2 = pos2)

                }else{
                    Thermometre(point = currentPos, point2 = pos2)
                }
                Log.e("Position 1", currentPos.toString())
                Log.e("Calcul", currentPos.getDistance(pos2).toInt().toString())

                if (lon != null && lat != null) {
                    DisplayLocation()
                }
            }
        }
    }
    @Composable
    fun magicalButton(){
        Button(onClick = { toggleLocationUpdates() }) {
            Text(text = if (lon != null && lat != null) "Stop Location Updates" else "Start Location Updates")
        }}

    private fun toggleLocationUpdates() {
        lifecycleScope.launch {
            if (lon != null && lat != null) {

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


