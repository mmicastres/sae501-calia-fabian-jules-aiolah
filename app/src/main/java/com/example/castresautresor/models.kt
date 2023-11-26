package com.example.castresautresor

// Classe des catégories
data class Categories(
    val docs: List<Categorie> = listOf()
)

data class Categorie(
    val nom: String = "",
    val chemin: String = "",
    val contentDescription: String = ""
)