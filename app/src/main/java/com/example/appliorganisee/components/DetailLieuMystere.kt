package com.example.castresautresor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel
import com.google.firebase.auth.FirebaseUser


// Détails d'un lieu mystère
@Composable
fun DetailLieuMystere(
    idLieu: String,
    viewModel: CatViewModel,
    navController: NavController,
    currentUser: FirebaseUser?
){
    if (currentUser != null) {
        viewModel.getLieuxByUid(currentUser.uid)
    }
    val lieumystere by viewModel.lieumystere.collectAsStateWithLifecycle()
    val imageurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieumystere.imageUrl

    LaunchedEffect(key1 = true) { viewModel.getLieuMystere(idLieu) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text ="Lieu mystère n°"+ lieumystere.idLieu,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = imageurl,
            contentDescription = "Image du lieu mystère n° ${lieumystere.imageUrl}",
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .blur(
                    radiusX = 30.dp,
                    radiusY = 30.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )
        Spacer(modifier = Modifier.height(35.dp))
        Card(modifier = Modifier
            .width(300.dp),
            shape = RoundedCornerShape(20)
        ) {
            Text(text = lieumystere.descriptionMystere,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                textAlign = TextAlign.Justify)
        }

        Button(
            onClick = {
                val lat = lieumystere.lat;
                val lon = lieumystere.long;
                navController.navigate("jeu/${lat}&${lon}&${lieumystere.idLieu}")},
            colors = ButtonDefaults.buttonColors(Color(0xFFEEBD0F))
        ) {
            Text("Commencer la recherche", fontSize = 17.sp)
        }


    }
}