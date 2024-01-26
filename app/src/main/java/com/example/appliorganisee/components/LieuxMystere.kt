package com.example.castresautresor

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel
import com.example.appliorganisee.Utlis.ApiLieux
import com.google.firebase.auth.FirebaseUser

// Liste de tous les lieux mystère de l'application
@Composable
fun LieuxMystere(
    navController: NavHostController,
    viewModel: CatViewModel
) {
    //Appel de viewModel.getLieuxMystere() une seule fois => Première apparition du composant LieuxMystere
    val lieuxMystere by viewModel.lieuxmystere.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) { viewModel.getLieuxMystere() }
    LaunchedEffect(key1 = true){ viewModel.fromCategorie("a827a373179dac87f50698d0c83ffe83")}

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

        items(lieuxMystere) {lieumystere ->
            CardLieu(lieumystere, navController)
        }
    }
}