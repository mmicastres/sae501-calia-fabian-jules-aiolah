package com.example.appliorganisee.Utlis

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/categories")
    suspend fun categories(): Categories

    @GET("/lieux")
    suspend fun lieuxmystere(): ApiLieuxResult

    @GET("lieu/{idLieu}")
    suspend fun lieumystere(@Path("idLieu") idLieu:String) : ApiLieux

    @GET("lieux/{idCategorie}")
    suspend fun categorie(@Path("idCategorie") idCategorie:String) : ApiLieuxResult
}