package com.example.appliorganisee;

import android.R.attr.password
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appliorganisee.Utlis.Api
import com.example.appliorganisee.Utlis.Categorie
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.appliorganisee.Utlis.ApiLieux
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CatViewModel: ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api-nodejs-sae.onrender.com/")
        //.baseUrl("https://aiolah.alwaysdata.net/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    var categories = MutableStateFlow<List<Categorie>>(listOf())
    val lieuxmystere = MutableStateFlow<List<ApiLieux>>(listOf())
    val lieumystere = MutableStateFlow(ApiLieux())
    val categorie = MutableStateFlow<List<ApiLieux>>(listOf())

    // Se lance à la création de la classe MainActivity
    /*init {
        getCategories()
    }*/

    fun getCategories() {
        viewModelScope.launch {
            categories.value = api.categories().docs
        }
    }

    fun getLieuxMystere(){
        viewModelScope.launch {
            lieuxmystere.value= api.lieuxmystere().docs
        }
    }

    fun getLieuMystere(idLieu:String){
        viewModelScope.launch {
            lieumystere.value= api.lieumystere(idLieu)
        }
    }

    fun fromCategorie(idCategorie:String){
        viewModelScope.launch {
            categorie.value = api.categorie(idCategorie).docs
        }
    }
}