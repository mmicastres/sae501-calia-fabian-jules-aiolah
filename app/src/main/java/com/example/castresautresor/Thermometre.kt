package com.example.castresautresor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    val distance_point = 99

    val color = getColor(distance_point)
    val pos2 = Position(43.6209, 2.2612)



    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val modificator = density.density * 0.450f

    //val widthH = configuration.screenWidthDp.toFloat() * modificator
    val heightmodificator = modificator / 1.3

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
                                    drawCircle(color, radius = 25f)
                                }
                            }
                        }
                    }
                }
            }

        }
    }else{
        Text(text = "Bonjour")
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
/*
@Composable
fun DrawingScreen() {

    Canvas(modifier = Modifier.fillMaxSize(),) {
        val canvasWidth = 0f
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = canvasWidth, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 21.dp.toPx(),
        )
        Log.i("CoHauteur", canvasHeight.toString())
    }
}
    /*Canvas(
        modifier = Modifier.size(25.dp)
            /*.pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position
                    )

                    lines.add(line)
                }
            }*/
    ) {inset(485.0F, distance_point.toFloat()) {
        val quadrantSize = size / 2.0f

        // Draw a rectangle within the inset bounds
        drawRect(
            size = quadrantSize,
            color = Color.Red
        )

    }
        /*
        lines.forEach { line ->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
        }

         */
    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)

*/