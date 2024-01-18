package com.example.appliorganisee.components

import android.util.Log
import com.example.appliorganisee.R
import com.example.appliorganisee.Utlis.Position
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.zIndex


@Composable
fun Thermometre(point: Position, point2: Position) {

    val distance_point = point.getDistance(point2).toInt()

    val color = getColor(distance_point)

    var QrcodeVisible by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val modificator = density.density * 0.490f

    //val widthH = configuration.screenWidthDp.toFloat() * modificator
    val heightmodificator = modificator / 1.3
    if (QrcodeVisible) {
        ShowQRCode()
    }

    BottomRightIconButton(){ QrcodeVisible = !QrcodeVisible
    }
    if (distance_point in 0..99) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    //Text(text = width.toString(), modifier = Modifier.clickable { viewModel.decrementer_compteur() })
                    Image(
                        painterResource(id = R.drawable.thermometre),
                        contentDescription = "Thermomètre",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(2f)
                    )
                    Canvas(modifier = Modifier.fillMaxSize(),) {


                        val width = configuration.screenWidthDp.toFloat() * modificator
                        val height =
                            configuration.screenHeightDp.toFloat() * 2.63f * heightmodificator
                        val start = height * 0.112f
                        val end = height * 0.65
                        val relativdistance = (distance_point / 100f) * (end - start).toFloat()
                        inset(horizontal = 0f, vertical = 0f) {
                            drawLine(
                                start = Offset(x = width, y = start.toFloat() + relativdistance),
                                end = Offset(x = width, y = end.toFloat()),
                                color = color,
                                strokeWidth = 25.dp.toPx(),
                            )
                            translate(top = height.toFloat() / 4) {
                                scale(scaleX = width / 89, scaleY = height.toFloat() / 170) {
                                    drawCircle(color, radius = 26f)
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
fun getColor(distancePoint: Int): Color {
    return when {
        distancePoint in 0..49 -> {
            val redValue = (255 * distancePoint) / 50 // Variation de rouge
            Color(255, redValue, 0) // Rouge à jaune
        }
        distancePoint in 50..74 -> {
            val blueGreenValue = ((distancePoint - 50) * 255) / 25 // Variation du bleu et du vert
            Color(255 - blueGreenValue, 255 - blueGreenValue, blueGreenValue) // Jaune à bleu
        }
        distancePoint in 75..99 -> {
            val blueValue = ((distancePoint - 75) * 255) / 25 // Variation de bleu
            Color(blueValue, blueValue, 255) // Transition progressive vers le bleu
        }
        else -> throw IllegalArgumentException("La distance doit être entre 0 et 99.")
    }
}

@Composable
fun BottomRightIconButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 0.dp, 100.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        IconButton(
            onClick =  onClick,
            modifier = Modifier
                .size(60.dp)
                .padding(bottom = 7.dp)
                .fillMaxSize()
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.icone_qrcode),
                contentDescription = "Icône"
            )
        }
    }
}