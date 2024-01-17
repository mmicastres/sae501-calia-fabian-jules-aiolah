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

// Liste de tous les lieux mystère de l'application
@Composable
fun LieuxMystere(navController: NavHostController, viewModel: CatViewModel) {
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