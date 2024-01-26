package com.example.appliorganisee

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appliorganisee.Utlis.Position
import com.example.appliorganisee.components.Accueil
import com.example.appliorganisee.components.Categories
import com.example.appliorganisee.components.Profil
import com.example.appliorganisee.components.Collection
import com.example.appliorganisee.components.Compass
import com.example.appliorganisee.components.Thermometre
import com.example.appliorganisee.ui.theme.AppliOrganiseeTheme
import com.example.appliorganisee.ui.theme.Vert
import com.example.castresautresor.DetailLieuMystere
import com.example.castresautresor.LieuxMystere
import com.example.castresautresor.LieuxMystereFromCategorie
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

class MainActivity : ComponentActivity() {

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    var lon by mutableStateOf<Double?>(null)
    var lat by mutableStateOf<Double?>(null)

    var currentPos by mutableStateOf(Position(0.0, 0.0))
    var currentGeoPoint by mutableStateOf(GeoPoint(0.0,0.0))


    lateinit var homeScreenMapView: MapView
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates()
                } else {
                    // Permission refusée
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        // Variables obligatoires pour l'authentification Firebase

        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        var deconnecte = mutableStateOf(auth.currentUser == null);
        super.onCreate(savedInstanceState)
        if (checkLocationPermission()) {
            startLocationUpdates()
        } else {
            requestLocationPermission()
        }

        Configuration.getInstance().userAgentValue = packageName
        homeScreenMapView = MapView(baseContext)
        homeScreenMapView.controller.setCenter(currentGeoPoint)
        setContent {
            val catViewModel: CatViewModel = viewModel()

            AppliOrganiseeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route


                Scaffold(

                    bottomBar =
                        {if(currentDestination != "jeu/{lat}&{lon}&{idlieu}"){

                            if (currentDestination != null) {
                                Log.e("currentlabel",currentDestination)
                            }
                            BottomNavigation(backgroundColor = Vert) {
                                destinations.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            androidx.compose.material3.Icon(
                                                painter = painterResource(screen.icon), "Icône",
                                                modifier = Modifier.height(22.dp)
                                                    .padding(bottom = 7.dp)
                                            )
                                        },
                                        label = { Text(screen.label) },
                                        selected = false,
                                        onClick = { navController.navigate(screen.destination) })
                                }
                            }
                        }}){ innerPadding ->

                    NavHost(navController = navController, startDestination = "Accueil") {
                        Modifier.padding(innerPadding)


                        // Attribution des données de l'utilisateur
                        var currentUser = auth.currentUser

                        composable("Accueil"){ Accueil(homeScreenMapView)}

                        composable("Profil") {
                            Log.e("currentuser", currentUser.toString())
                            if (deconnecte.value == false) {
                                Column() {

                                    if (currentUser != null) {
                                        auth.currentUser?.let {
                                            catViewModel.postUtil(it)
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp)
                                        ) {

                                            currentUser!!.email?.let { it1 -> UserInformationItem(icon = Icons.Default.Email, label = "Email", value = it1) }


                                            Button(
                                                onClick = {
                                                    deconnecte.value = true; currentUser = null; auth.signOut()
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                   Color(0xFFEEBD0F)
                                                ),
                                                modifier = Modifier

                                                    .fillMaxWidth()
                                                    .padding(top = 16.dp),
                                            ) {
                                                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(text = "Déconnexion")

                                            }
                                        }
                                        Button(onClick = {
                                            deconnecte.value = true; currentUser = null; auth.signOut()
                                        }) {
                                            Text(text = "Déconnexion")
                                        }
                                    }else{
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.SpaceAround,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                painter = painterResource(R.drawable.lutin_grognon_content),
                                                contentDescription = "t'as rien",
                                                modifier = Modifier
                                                    .padding(0.dp, 60.dp, 0.dp, 0.dp)
                                                    .size(500.dp)
                                            )

                                            androidx.compose.material.Text(
                                                text = "Bravo, tu es connecté !",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 30.sp
                                            )
                                        }
                                    }
                                }
                            }else{
                                Profil(auth, deconnecte, catViewModel)
                            }}

                        // --- Route des catégories
                        composable("Categories") { Categories(navController, catViewModel) }

                        // --- Routes des lieux mystère
                        // Composant LieuxMystere
                        composable(Destination.LieuxMystere.destination) {
                            LieuxMystere(navController, catViewModel)
                        }

                        // Composant LieuxMystereFromCategorie
                        composable(
                            "categorie/{idCategorie}"
                        ) { backStackEntry ->
                            LieuxMystereFromCategorie(navController,backStackEntry.arguments?.getString("idCategorie") ?: "", catViewModel)
                        }

                        // Composant DetailLieuMystere
                        composable(
                            "lieu/{idLieu}"
                        ) { backStackEntry ->
                            DetailLieuMystere(
                                backStackEntry.arguments?.getString("idLieu") ?: "",
                                catViewModel, navController,currentUser
                            )
                        }

                        // Route boussole / thermo

                        composable(Destination.Jeu.destination, arguments = listOf(
                            navArgument("lat") { type = NavType.StringType },
                            navArgument("lon") { type = NavType.StringType },

                        )){
                            var lat = navBackStackEntry?.arguments?.getString("lat")?.toDouble()
                            var lon = navBackStackEntry?.arguments?.getString("lon")?.toDouble()

                            if(lat != null && lon != null){
                                var pos2 = Position(lat, lon)
                                if (currentPos.getDistance(pos2).toInt() > 99) {
                                    Log.e("Fonctionne", "Affichée")
                                    Compass(point = pos2, point2 = currentPos)
                                } else {
                                    Thermometre(point = currentPos, point2 = pos2, catViewModel, currentUser, navController)
                                }
                            }
                        }

                        composable(Destination.LieuDecouvert.destination) {
                                backStackEntry ->
                            LieuDecouvert(backStackEntry.arguments?.getString("idLieu") ?: "", catViewModel)

                        }

                        composable("Collection") {

                            Collection(catViewModel,currentUser, navController)
                        }
                    }
                }

            }
        }
    }

    // Destinations des routes

    sealed class Destination(val destination: String, val label: String, val icon: Int) {
        object Accueil : Destination("accueil", "Accueil", R.drawable.icone_collection)
        object Profil : Destination("profil", "Profil", R.drawable.icone_profil)
        object Categories : Destination("categories", "Catégories", R.drawable.icone_categories)
        object Collection : Destination("collection", "Collection", R.drawable.icone_collection)
        object Categorie: Destination("categorie/{id}", "Catégorie", R.drawable.icone_collection)
        object LieuxMystere : Destination("lieux", "LieuxMystere", R.drawable.icone_collection)
        object DetailLieuMystere : Destination("lieu/{idLieu}", "LieuMystere", R.drawable.icone_collection)
        object Jeu  : Destination("jeu/{lat}&{lon}&{idlieu}", "Jeu", R.drawable.icone_collection)
        object LieuDecouvert : Destination("lieudecouvert/{idLieu}", "LieuxDecouvert", R.drawable.icone_collection)
    }
    val destinations = listOf(Destination.Profil, Destination.Categories, Destination.Collection)




    // Fonctions de localisation


    private fun startLocationUpdates() {
        lifecycleScope.launch {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).apply {
                setMinUpdateDistanceMeters(1F)
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            try {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    null
                )
            } catch (e: SecurityException) {
                // Gérer l'exception de sécurité ici
            }
        }
    }

    private fun stopLocationUpdates() {
        lifecycleScope.launch {
            try {
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            } catch (e: SecurityException) {
                // Gérer l'exception de sécurité ici
            }
            var lon = null
            var lat = null
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
    }
    val locationCallback = object : LocationCallback() {
        @SuppressLint("SuspiciousIndentation")
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation = locationResult.lastLocation
         lon = lastLocation?.longitude
         lat = lastLocation?.latitude

            if(lat != null){
                currentPos = Position(lat!!, lon!!)



                currentGeoPoint.latitude = lat!!
                currentGeoPoint.longitude = lon!!}
                homeScreenMapView.controller.setCenter(currentGeoPoint)

        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 101
    }
    @Composable
    fun UserInformationItem(icon: ImageVector, label: String, value: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Affichage de l'icône
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))

            // Affichage du libellé et de la valeur
            Column {
                Text(text = label, color = Color.Gray, fontSize = 12.sp)
                Text(text = value, fontSize = 16.sp)
            }
        }
    }
}

