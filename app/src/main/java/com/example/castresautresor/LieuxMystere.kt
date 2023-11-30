package com.example.castresautresor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LieuxMystere(navController: NavHostController) {
    val viewModel = MainViewModel()
    //Appel de viewModel.getLieuxMystere() une seule fois => Première apparition du composant LieuxMystere
    val lieuMystere by viewModel.lieuxmystere.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) { viewModel.getLieuxMystere() }

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

        for (lieumystere in lieuMystere){
            val imageurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieumystere.imageUrl
            items(1){
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
        }

    }

}

@Composable
fun LieuMystere(idLieu:String, viewModel:MainViewModel){
    Text(text = idLieu)
    val lieumystere by viewModel.lieumystere.collectAsStateWithLifecycle()
    val imageurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieumystere.imageUrl

    LaunchedEffect(key1 = true) { viewModel.getLieuMystere(idLieu) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        Text(
            text ="Lieu mystère n° "+ lieumystere.idLieu.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        AsyncImage(
            model = imageurl,
            contentDescription = "Image du lieu mystère n° ${lieumystere.imageUrl}",
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .blur(
                    radiusX = 20.dp,
                    radiusY = 15.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )
        Text(text = "${lieumystere.descriptionMystere}")
        @Composable
        fun ButtonBousole(onClick: () -> Unit) {
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(Color(0xFFEEBD0F))
            ) {
                Text("Commencer la recherche")
            }
        }
    }



}
