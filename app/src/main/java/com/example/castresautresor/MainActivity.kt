package com.example.castresautresor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.NavigationRes
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.castresautresor.ui.theme.CastresAuTresorTheme
import com.example.premiereapplication.MainViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
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

sealed class Destination(val destination: String, val label: String)
{
    object Categories: Destination("categories", "Catégories")
    object Categorie: Destination("categorie/{id}", "Catégorie")
}

@Composable
fun Navigation() {
    val navController = rememberNavController();
    val navBackStackEntry by navController.currentBackStackEntryAsState();

    var currentDestination = navBackStackEntry?.destination?.route;

    NavHost(navController, startDestination = Destination.Categories.destination) {
        composable(Destination.Categories.destination) { Categories(navController) }
        composable(
            Destination.Categorie.destination,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            var id = navBackStackEntry?.arguments?.getString("id")
            InsideCategorie(id, navController)
        }
    }
}