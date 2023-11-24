package com.example.castresautresor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.premiereapplication.MainViewModel

@Composable
fun Categorie(categorie: Categorie) {
    Row {
        Text(text = categorie.nom)
    }
}
@Composable
fun Categories() {
    val viewModel = MainViewModel()

    val categories by viewModel.categories.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) { viewModel.getCategories() }

    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(categories) { categorie ->
            Categorie(categorie)
        }
    }
}