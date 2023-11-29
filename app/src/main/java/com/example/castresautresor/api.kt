package com.example.castresautresor

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api{
    @GET("/lieux")
    suspend fun lieuxmystere():ApiLieuxResult

    @GET("lieux/{id}")
    suspend fun lieumystere(@Path("id") id:String) : ApiLieux
}