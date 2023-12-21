package com.example.formsutil

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.google.firebase.auth.FirebaseAuth

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Form(auth: FirebaseAuth, deconnecte: MutableState<Boolean>) {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Row( horizontalArrangement = Arrangement.Center) {
            Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Connexion")
                TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") }, maxLines = 1)
                TextField(value = password, onValueChange = {password = it}, label = { Text(text = "Mot de passe") },visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
                Button(
                    onClick = {
                        authentification(email, password, auth, deconnecte)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
                Text(text = "Pas de compte ?")
                FormInscr(auth, deconnecte)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormInscr(auth: FirebaseAuth, deconnecte: MutableState<Boolean>) {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Row( horizontalArrangement = Arrangement.Center) {
            Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Inscription")
                TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") }, maxLines = 1)
                TextField(value = password, onValueChange = {password = it}, label = { Text(text = "Mot de passe") },visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
                Button(
                    onClick = {
                        inscription(email, password, auth, deconnecte)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Inscription")
                }
            }
        }
    }