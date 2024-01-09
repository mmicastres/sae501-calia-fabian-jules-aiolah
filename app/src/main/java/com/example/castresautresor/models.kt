package com.example.castresautresor

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
    val dateDecouverte: String = "",
    val lat: String = "",
    val long: String = "",
    val nomLieu: String = ""
)