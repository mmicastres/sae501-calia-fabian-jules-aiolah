package com.example.castresautresor

data class ApiLieuxMystereResult(
    val docs: List<ApiLieuxMystere> = listOf()
)

data class ApiLieuxMystere(
    val categorie: String = "",
    val description: String = "",
    val idLieuxMystère: String = "",
    val imageUrl: String = ""
)