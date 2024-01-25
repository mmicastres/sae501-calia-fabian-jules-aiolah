package com.example.appliorganisee.Utlis

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import java.lang.reflect.Constructor
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

// Classe des catégories
data class Categories(
    val docs: List<Categorie> = listOf()
)

data class Categorie(
    val _id: String = "",
    val nom: String = "",
    val chemin: String = "",
    val contentDescription: String = ""
)


// Classe des lieux

data class ApiLieuxResult(
    val docs: List<ApiLieux> = listOf()
)

data class ApiLieux(
    val categorie: String = "",
    val description: String = "",
    val descriptionMystere: String = "",
    val idCategorie: String = "",
    val idLieu: Int = 0,
    val imageUrl: String = "",
    val badgeUrl: String = "",
    val lat: String = "",
    val long: String = "",
    val nomLieu: String = ""
)

// Classes concernant les utilisateurs

data class Util(
    val idUtil: String = "",
    val email: String = "",
    var LieuxDecouvert: List<LieuxDecouvert> = listOf(),

) {
    companion object {
        fun fromFirebase(utilisateur: FirebaseUser) =
            Util(utilisateur.uid, utilisateur.email ?: "", listOf())
    }
    fun addLieux(codes: List<LieuxDecouvert>) {
        LieuxDecouvert = LieuxDecouvert + codes
        Log.e("nouveau tableau", LieuxDecouvert.toString())
    }
}



data class LieuxDecouvert(
    val idLieu: String = "",

) {
    companion object {
        fun fromCode(code: String) =
            LieuxDecouvert(code)
    }
}