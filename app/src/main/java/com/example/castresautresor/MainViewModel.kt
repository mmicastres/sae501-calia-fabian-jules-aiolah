package com.example.premiereapplication

import android.R.attr.password
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.castresautresor.Api
import com.example.castresautresor.BasicAuthInterceptor
import com.example.castresautresor.Categorie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel: ViewModel() {
    var client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("aiolah", "6c6p8q20"))
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://couchdb-aiolah.alwaysdata.net:5984/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    var categories = MutableStateFlow<List<Categorie>>(listOf())

    fun getCategories() {
        viewModelScope.launch {
            categories.value = api.categories().docs
        }
    }
}