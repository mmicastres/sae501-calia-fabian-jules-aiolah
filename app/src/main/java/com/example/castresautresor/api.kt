package com.example.castresautresor

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api{
    @GET("/lieux")
    suspend fun lieuxmystere():ApiLieuxResult

    @GET("lieu/{idLieu}")
    suspend fun lieumystere(@Path("idLieu") idLieu:String) : ApiLieux
}