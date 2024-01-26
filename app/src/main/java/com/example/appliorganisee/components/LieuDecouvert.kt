package com.example.appliorganisee

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel

@Composable
fun LieuDecouvert(idLieu: String, viewModel: CatViewModel, ) {
    val lieudecouvert by viewModel.lieudecouvert.collectAsStateWithLifecycle()
    val imageurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieudecouvert.imageUrl
    val badgeurl = "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/Badge/" + lieudecouvert.badgeUrl

    Log.e("lieuaafficheravant",lieudecouvert.toString())
    LaunchedEffect(key1 = true) { viewModel.getLieuDecouvert(idLieu) }
    Log.e("lieuaafficherapres",lieudecouvert.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = lieudecouvert.nomLieu,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            // Première image
            AsyncImage(
                model = imageurl,
                contentDescription = "Image du lieu n° ${lieudecouvert.imageUrl}",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            // Deuxième image superposée en bas à droite
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 10.dp, 0.dp) // Augmentez les valeurs pour déplacer le badge
            ) {
                AsyncImage(
                    model = badgeurl,
                    contentDescription = "Badge du ${lieudecouvert.nomLieu}",
                    modifier = Modifier
                        .width(75.dp)
                        .height(70.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Card(
            modifier = Modifier
                .width(300.dp)
               , // Ajout du défilement vertical
            shape = RoundedCornerShape(20)
        ) {
            Text(
                text = "${lieudecouvert.description}",
                modifier = Modifier
                    .padding(horizontal = 25.dp, vertical = 25.dp),
                textAlign = TextAlign.Justify
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
    Spacer(modifier = Modifier.height(30.dp))
}

