package com.example.castresautresor

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun InsideCategorie(id: String?, navController: NavController) {
    if (id != null) {
        Text(text = id)
    }
}