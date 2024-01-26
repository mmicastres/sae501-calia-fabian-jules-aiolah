package com.example.appliorganisee.Utlis

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("lieudecouvert/{idLieu}")
    suspend fun lieudecouvert(@Path("idLieu") idLieu:String) : ApiLieux

    @GET("utilisateurslieux/{idUtil}")
    suspend fun getUtilLieux(@Path("idUtil")idUtil: String) : List<LieuxDecouvert>

    @POST("utilisateurs")
    suspend fun createUser(@Body utilisateur: Util):Response<Util>

    @PUT("utilisateurs/{idUtil}")
    suspend fun updateUser(@Path("idUtil") idUtil: String, @Body utilisateur: Util):Response<Util>
}