package com.example.castresautresor

import android.util.Log
import androidx.compose.runtime.Composable


@Composable
fun CompassOrThermometre() {
    val pos2 = Position(43.6210, 2.2661)
    if (currentPos.getDistance(pos2).toInt() > 99) {

        Compass(point = currentPos, point2 = pos2)

    }else{
        Thermometre(point = currentPos, point2 = pos2)
    }
    Log.e("Position 1", currentPos.toString())
    Log.e("Calcul", currentPos.getDistance(pos2).toInt().toString())

}