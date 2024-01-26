package com.example.appliorganisee.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appliorganisee.CatViewModel
import com.example.appliorganisee.Utlis.Categorie

// Liste des catégories
@Composable
fun Categories(navController: NavHostController, viewModel: CatViewModel) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) { viewModel.getCategories() }

    LazyVerticalGrid(columns = GridCells.Fixed(1), modifier = Modifier.padding(30.dp)) {
        item(span = { GridItemSpan(1) }) {
            Text(text = "Catégories", textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, fontSize = 30.sp)
        }

        Log.e("Objet", categories.toString())

        items(categories) { categorie ->
            Categorie(categorie, navController)
        }

        // Si la variable categories n'est pas vide, alors on affiche les catégories 😄 !
        /*if(!categories.isEmpty())
        {
            items(categories) { categorie ->
                Categorie(categorie, navController)
            }
        }*/
    }
}

// Une catégorie
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categorie(categorie: Categorie, navController: NavHostController) {
    // Log.e("Categorie", "ICI")
    Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(0),
        modifier = Modifier
            .padding(12.dp)
            .size(width = 50.dp, height = 60.dp,)
            .clickable { navController.navigate("categorie/${categorie._id}") }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
            AsyncImage(model = "https://aiolah-vaiti.fr/castres-au-tresor/images/${categorie.chemin}", contentDescription = categorie.contentDescription, modifier = Modifier
                .height(45.dp)
                .width(45.dp))
            Text(text = "    " + categorie.nom)
        }
    }
}
