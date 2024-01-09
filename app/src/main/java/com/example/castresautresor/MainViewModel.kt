package com.example.castresautresor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api-nodejs-sae.onrender.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    val lieudecouvert =  MutableStateFlow(ApiLieux())

    fun getLieuDecouvert(idLieu: String){
        viewModelScope.launch {
            lieudecouvert.value= api.lieudecouvert(idLieu)
        }
    }

}