package com.example.formsutil


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.formsutil.ui.theme.FormsUtilTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        var deconnecte = mutableStateOf(true);
        super.onCreate(savedInstanceState)
        setContent {
            FormsUtilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentUser = auth.currentUser
                    if (deconnecte.value == false) {
                        Column() {
                            Text(text = currentUser!!.email.toString())
                            Button(onClick = {deconnecte.value = true; currentUser = null;}) {
                                Text(text = "Déconnexion")
                            }
                        }
                    }else{
                        Column(verticalArrangement = Arrangement.SpaceAround) {
                            Form(auth, deconnecte)
                        }
                    }
                    
                }
            }
        }
    }
}

