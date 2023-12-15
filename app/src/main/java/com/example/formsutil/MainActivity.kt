package com.example.formsutil


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.formsutil.ui.theme.FormsUtilTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContent {
            FormsUtilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Check if user is signed in (non-null) and update UI accordingly.
                    var currentUser = auth.currentUser
                    if (currentUser != null) {
                        Column() {
                            Text(text = currentUser!!.email.toString())
                            Button(onClick = {currentUser = null}) {
                                Text(text = "Déconnexion")
                            }
                        }
                    }else{
                        Form(auth)
                    }
                    
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(auth: FirebaseAuth) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Row( horizontalArrangement = Arrangement.Center) {
        Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Connexion")
            TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") }, maxLines = 1)
            TextField(value = password, onValueChange = {password = it}, label = { Text(text = "Mot de passe")},visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
            Button(
                onClick = {
                    authentification(email, password, auth)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInscr(auth: FirebaseAuth) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Row( horizontalArrangement = Arrangement.Center) {
        Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Connexion")
            TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") }, maxLines = 1)
            TextField(value = password, onValueChange = {password = it}, label = { Text(text = "Mot de passe")},visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
            Button(
                onClick = {
                    inscription(email, password, auth)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}

fun authentification(email: String, password: String, auth: FirebaseAuth) {
    Log.e("WORK", "salut")
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Log.d("JUAN", "signInWithEmail:success")
            val user = auth.currentUser
        } else {
            // If sign in fails, display a message to the user.
            Log.w("JUAN", "signInWithEmail:failure", task.exception)

        }
    }
}

fun inscription(email: String, password: String, auth: FirebaseAuth){
auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
    if (task.isSuccessful) {
        // Sign in success, update UI with the signed-in user's information
        Log.d("JUAN", "createUserWithEmail:success")
        val user = auth.currentUser
    } else {
        // If sign in fails, display a message to the user.
        Log.w("JUAN", "createUserWithEmail:failure", task.exception)
    }
}
}

@Composable
fun Test(){
    Text(text = "Salut")
}