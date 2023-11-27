package com.example.castresautresor

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api{
    @GET("aiolah_castres_au_tresor_lieux/_find")
    suspend fun lieuxmystere():ApiLieuxMystereResult
}