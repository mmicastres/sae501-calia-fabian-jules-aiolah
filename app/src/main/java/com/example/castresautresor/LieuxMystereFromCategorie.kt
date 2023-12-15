package com.example.castresautresor

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun LieuxMystereFromCategorie(navController: NavHostController, idCategorie:String, viewModel: MainViewModel) {
    val viewModel = MainViewModel()
    //Appel de viewModel.getLieuxMystere() une seule fois => Première apparition du composant LieuxMystere
    val lieuFromCategorie by viewModel.categorie.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) { viewModel.getLieuxMystere() }
    LaunchedEffect(key1 = true){ viewModel.fromCategorie(idCategorie)}

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

        items(lieuFromCategorie) {lieumystere ->
            Lieu(lieumystere, navController)
        }
    }
}