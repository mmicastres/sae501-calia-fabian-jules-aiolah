package com.example.appliorganisee.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import com.example.appliorganisee.R
import com.example.appliorganisee.Utlis.Position
import org.osmdroid.views.MapView

@Composable
fun Compass(point: Position, point2: Position) { val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var azimuth by remember { mutableStateOf(0f) }

    DisposableEffect(key1 = sensorManager) {
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        val accelerometerReading = FloatArray(3)
        val magneticFieldReading = FloatArray(3)

        val rotationMatrix = FloatArray(9)
        val orientationValues = FloatArray(3)

        val sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent?) {
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> System.arraycopy(
                        event.values,
                        0,
                        accelerometerReading,
                        0,
                        accelerometerReading.size
                    )
                    Sensor.TYPE_MAGNETIC_FIELD -> System.arraycopy(
                        event.values,
                        0,
                        magneticFieldReading,
                        0,
                        magneticFieldReading.size
                    )
                }

                SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magneticFieldReading)
                SensorManager.getOrientation(rotationMatrix, orientationValues)

                // Convert radians to degrees for azimuth
                val azimuthRadians = orientationValues[0]
                azimuth = Math.toDegrees(azimuthRadians.toDouble()).toFloat()
            }
        }

        sensorManager.registerListener(
            sensorEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            sensorEventListener,
            magneticField,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    CompassDraw(point = point, point2 = point2, azimuth =azimuth )
}

@Composable
fun CompassDraw(point: Position, point2: Position, azimuth: Float) {
    val rotation = point.getAngle(point2).toFloat()-azimuth
    Row(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.compass),
            contentDescription = "Compass",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .zIndex(2f)
                .fillMaxSize()
                .rotate(rotation)

        )

    }
}
