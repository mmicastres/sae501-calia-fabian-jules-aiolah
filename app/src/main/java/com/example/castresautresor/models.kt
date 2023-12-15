package com.example.castresautresor

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