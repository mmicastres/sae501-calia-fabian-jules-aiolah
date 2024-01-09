package com.example.castresautresor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.castresautresor.ui.theme.CastresAuTresorTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CastresAuTresorTheme() {
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
    val TEMPORAIRE = 1

    Scaffold { innerPadding ->
        NavHost(
            navController, startDestination = Destination.LieuDecouvert.destination,
            Modifier.padding(innerPadding)
        ) {
            composable(Destination.LieuDecouvert.destination) {
                LieuDecouvert(TEMPORAIRE.toString(),  viewModel)
            }
        }
    }

}
sealed class Destination(val destination: String, val label: String, val icon: ImageVector) {
    object LieuDecouvert : Destination("lieudecouvert/{idLieu}", "LieuxDecouvert", Icons.Filled.AccountBox)

}