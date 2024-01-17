package com.example.castresautresor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel
import com.example.appliorganisee.Utlis.ApiLieux

// Carte d'un lieu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardLieu(lieumystere: ApiLieux, navController: NavHostController)
{
    val imageurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieumystere.imageUrl
    Card(
        onClick = {navController.navigate("lieu/${lieumystere.idLieu}")},
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(width = 100.dp)
            .padding(5.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)) {
            AsyncImage(
                model = imageurl,
                contentDescription = "Image du lieu mystere n° ${lieumystere.imageUrl}",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .blur(
                        radiusX = 20.dp,
                        radiusY = 15.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    )
            )
            Row(modifier = Modifier .height(75.dp)) {
                Text(text = lieumystere.descriptionMystere,)
            }
        }
    }
}