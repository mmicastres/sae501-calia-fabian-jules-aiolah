package com.example.castresautresor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun LieuMystere(lieuxMystere: ApiLieuxMystere) {
    Row {
        Text(text = lieuxMystere.idLieuxMystère)
    }
}
@Composable
fun LieuxMystere() {
    val viewModel = MainViewModel()
    //Appel de viewModel.getLieuxMystere() une seule fois => Première apparition du composant LieuxMystere
    val lieuMystere by viewModel.lieuxmystere.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) { viewModel.getLieuxMystere() }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        for (lieumystere in lieuMystere){
            items(1){
                Text(text = lieumystere.idLieuxMystère)
            }
        }

    }

}
