package com.example.castresautresor

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){
    var client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("aiolah_calia", "Fire_horse84"))
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api-nodejs-sae.onrender.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    val lieuxmystere = MutableStateFlow<List<ApiLieux>>(listOf())
    val lieumystere = MutableStateFlow<ApiLieux>(ApiLieux())
    //Penser a ajouter


    fun getLieuxMystere(){
        viewModelScope.launch {
            lieuxmystere.value= api.lieuxmystere().docs
        }
    }

    fun getLieuMystere(idLieux:String){
        viewModelScope.launch {
            lieumystere.value= api.lieumystere(idLieux)
        }
    }
}