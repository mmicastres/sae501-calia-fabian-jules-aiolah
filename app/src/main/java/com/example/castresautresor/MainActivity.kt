package com.example.castresautresor

import MainViewModel
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.NavigationRes
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.castresautresor.ui.theme.CastresAuTresorTheme
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CastresAuTresorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState();
    var currentDestination = navBackStackEntry?.destination?.route;


    Scaffold { innerPadding ->
        NavHost(
            navController, startDestination = Destination.Categories.destination,
            Modifier.padding(innerPadding)
        ) {
            // Composant Categories
            composable(Destination.Categories.destination) { Categories(navController) }
            // Composant InsideCategorie
            composable(
                Destination.Categorie.destination,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                var id = navBackStackEntry?.arguments?.getString("id")
                InsideCategorie(id, navController)
            }

            // Composant LieuxMystere
            composable(Destination.LieuxMystere.destination) {
                LieuxMystere(navController)
            }

            // Composant LieuxMystereFromCategorie
            composable(
                "categorie/{idCategorie}"
            ) { backStackEntry ->
                LieuxMystereFromCategorie(navController,backStackEntry.arguments?.getString("idCategorie") ?: "", viewModel)
            }

            // Composant LieuMystere
            composable(
                "lieu/{idLieu}"
            ) { backStackEntry ->
                LieuMystere(backStackEntry.arguments?.getString("idLieu") ?: "", viewModel)
            }
        }
    }
}

sealed class Destination(val destination: String, val label: String) {
    object Categories: Destination("categories", "Catégories")
    // object Categorie: Destination("categorie/{id}", "Catégorie")
    object LieuxMystere : Destination("lieux", "LieuxMystere")
    object LieuMystere : Destination("lieu", "LieuMystere")
    object Categorie : Destination("categorie/{idCategorie}", "Lieux d'une catégorie")
}