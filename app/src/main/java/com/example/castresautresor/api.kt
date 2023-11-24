package com.example.castresautresor

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import okhttp3.Credentials
import okhttp3.Interceptor

interface Api {
    @POST("aiolah_castres_au_tresor_categories/_find")
    suspend fun categories(): Categories
}