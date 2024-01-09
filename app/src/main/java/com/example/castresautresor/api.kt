package com.example.castresautresor

import retrofit2.http.GET
import retrofit2.http.Path

interface Api{
    @GET("lieudecouvert/{idLieu}")
    suspend fun lieudecouvert(@Path("idLieu") idLieu:String) : ApiLieux
}