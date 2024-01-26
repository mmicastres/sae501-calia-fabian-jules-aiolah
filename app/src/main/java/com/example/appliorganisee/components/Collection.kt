
package com.example.appliorganisee.components


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel
import com.example.appliorganisee.R
import com.example.appliorganisee.Utlis.ApiLieux
import com.google.firebase.auth.FirebaseUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Collection(
    catViewModel: CatViewModel,
    currentUser: FirebaseUser?,
    navController: NavHostController
) {

    if (currentUser != null) {

        val lieux by catViewModel.lieuxmystere.collectAsStateWithLifecycle()
        val lieuxutilisateur by catViewModel.lieuxutilisateur.collectAsStateWithLifecycle()
        LaunchedEffect(key1 = true) { catViewModel.getLieuxMystere() }
        LaunchedEffect(key1 = true) { catViewModel.getLieuxByUid(currentUser.uid) }

        val lieuxFiltres = lieux.filter { lieu ->
            lieuxutilisateur.any { lieuDecouvert ->
                lieuDecouvert.idLieu == lieu.idLieu.toString()
            }
        }

        if (lieuxFiltres.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.lutin_grognon),
                    contentDescription = "t'as rien",
                    modifier = Modifier
                        .padding(0.dp, 60.dp, 0.dp, 0.dp)
                        .size(200.dp)
                )

                Text(
                    text = "T'as rien !",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

        }


            LazyVerticalGrid(columns = GridCells.Fixed(1), modifier = Modifier.padding(30.dp),) {
                item(span = { GridItemSpan(1) }) {
                    Text(
                        text = "Collection",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }

                items(lieuxFiltres) { lieu ->
                    val imageurl =
                        "https://webmmi.iut-tlse3.fr/~clc4232a/S5/SAE501/" + lieu.imageUrl
                    Card(
                        onClick = { navController.navigate("LieuDecouvert/${lieu.idLieu}") },
                        border = BorderStroke(1.dp, Color.LightGray),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .width(width = 20.dp)

                    ) {
                        Column() {
                            AsyncImage(
                                model = imageurl,
                                contentDescription = "Image du lieu mystere n° ${lieu.imageUrl}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .height(200.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .blur(
                                        radiusX = 20.dp,
                                        radiusY = 15.dp,
                                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                                    )
                            )
                            Row(modifier = Modifier.height(20.dp)) {
                                androidx.compose.material3.Text(
                                    text = lieu.nomLieu, textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }


            }

        }


    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Collection",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Image(
                painter = painterResource(R.drawable.lutin_grognon),
                contentDescription = "Va te connecter !!",
                modifier = Modifier
                    .padding(0.dp, 60.dp, 0.dp, 0.dp)
                    .size(200.dp)
            )

            Text(
                text = "Va te connecter !",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Button(
                onClick = {
                    navController.navigate("Profil")
                },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFEEBD0F)
                ),
                modifier = Modifier

                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.material3.Text(text = "Page connexion")

            }
        }


    }

}
