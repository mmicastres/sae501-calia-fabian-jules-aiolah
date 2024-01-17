package com.example.castresautresor

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.appliorganisee.CatViewModel

// Liste des lieux mystère d'une catégorie
@Composable
fun LieuxMystereFromCategorie(navController: NavHostController, idCategorie:String, viewModel: CatViewModel) {
    val lieuxFromCategorie by viewModel.categorie.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true){ viewModel.fromCategorie(idCategorie)}

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

        items(lieuxFromCategorie) {lieumystere ->
            CardLieu(lieumystere, navController)
        }
    }
}