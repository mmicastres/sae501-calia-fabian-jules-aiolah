package com.example.appliorganisee;

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appliorganisee.Utlis.Api
import com.example.appliorganisee.Utlis.Categorie
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.appliorganisee.Utlis.ApiLieux
import com.example.appliorganisee.Utlis.LieuxDecouvert
import com.example.appliorganisee.Utlis.Util
import com.google.firebase.auth.FirebaseUser
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
    val utilisateur = MutableStateFlow(Util())
    val lieuxutilisateur = MutableStateFlow<List<LieuxDecouvert>>(listOf())
    val lieudecouvert =  MutableStateFlow(ApiLieux())
    val lieuxdecouvert =  MutableStateFlow<List<ApiLieux>>(listOf())

    // Se lance à la création de la classe MainActivity

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

    // Récupération des infos du lieu scanné

    fun getLieuDecouvert(idLieu: String){
        viewModelScope.launch {
            lieudecouvert.value = api.lieudecouvert(idLieu)
        }

    }

    // Se lance à l'inscription

    // Ajout d'un utilisateur dans la BDD

    fun postUtil(user: FirebaseUser){
        viewModelScope.launch{
            val myuser = Util.fromFirebase(user)
            utilisateur.value = myuser
            api.createUser(myuser)
        }
    }

    // Modification dudit utilisateur

    fun putUtil(user: FirebaseUser, code: String = ""){
        viewModelScope.launch {

            val myuser = Util.fromFirebase(user)
            myuser.addLieux(lieuxutilisateur.value)
            Log.e("Avant addLieux", myuser.toString())
            var code2 = LieuxDecouvert.fromCode(code)
            myuser.addLieux(listOf(code2))
            Log.e("Après addLieux", myuser.toString())
            utilisateur.value = myuser
            api.updateUser(myuser.idUtil, myuser)
        }
    }

    // Récupération des lieux découverts de l'utilisateur connecté

    fun getLieuxByUid(useruid: String){
        viewModelScope.launch {
            lieuxutilisateur.value = api.getUtilLieux(useruid);
            Log.e("lieuxutilisateur", lieuxutilisateur.value.toString())
        }
    }

}