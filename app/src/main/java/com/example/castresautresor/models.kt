package com.example.castresautresor

data class ApiLieuxResult(
    val docs: List<ApiLieux> = listOf(),
)

data class ApiLieux(
    val _id: String = "",
    val categorie: String = "",
    val description: String = "",
    val descriptionMystere: String = "",
    val idCategorie: String = "",
    val idLieu: String = "",
    val idLieuxMystere: String = "",
    val imageUrl: String = "",
    val nomLieu: String = ""
)