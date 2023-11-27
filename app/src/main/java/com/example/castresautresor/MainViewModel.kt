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
        .baseUrl("https://couchdb-aiolah.alwaysdata.net:5984/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    val lieuxmystere = MutableStateFlow<List<ApiLieuxMystere>>(listOf())

    fun getLieuxMystere(){
        viewModelScope.launch {
            lieuxmystere.value= api.lieuxmystere().docs
        }
    }

    fun getLieuMystere(id:String){
        viewModelScope.launch {
            //lieuxmystere.value= api.lieuxmystere().docs
        }
    }
}