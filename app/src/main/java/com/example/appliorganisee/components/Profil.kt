package com.example.appliorganisee.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appliorganisee.CatViewModel
import com.example.appliorganisee.Utlis.authentification
import com.example.appliorganisee.Utlis.inscription
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profil(auth: FirebaseAuth, deconnecte: MutableState<Boolean>, catViewModel: CatViewModel) {
   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         text = "Connexion",
         style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
         modifier = Modifier.padding(bottom = 16.dp)
      )
      TextField(
         value = email,
         onValueChange = { email = it },
         label = { Text(text = "Email") },
         maxLines = 1,
         modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
      )
      TextField(
         value = password,
         onValueChange = { password = it },
         label = { Text(text = "Mot de passe") },
         visualTransformation = PasswordVisualTransformation(),
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
         modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
      )
      Button(
         onClick = {
            authentification(email, password, auth, deconnecte);
         },
         colors = ButtonDefaults.buttonColors(
            Color(0xFFEEBD0F)
         ),
         modifier = Modifier
            .fillMaxWidth()

      ) {
         Text("Connexion", color = Color.Black)
      }
      Text(
         text = "Pas de compte ?",
         modifier = Modifier.padding(top = 16.dp)
      )
      FormInscr(auth, deconnecte, catViewModel)
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInscr(auth: FirebaseAuth, deconnecte: MutableState<Boolean>, catViewModel: CatViewModel) {
   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         text = "Inscription",
         style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
         modifier = Modifier.padding(bottom = 16.dp)
      )
      TextField(
         value = email,
         onValueChange = { email = it },
         label = { Text(text = "Email") },
         maxLines = 1,
         modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
      )
      TextField(
         value = password,
         onValueChange = { password = it },
         label = { Text(text = "Mot de passe") },
         visualTransformation = PasswordVisualTransformation(),
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
         modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
      )
      Button(
         onClick = {
            inscription(email, password, auth, deconnecte, catViewModel)
         },
         colors = ButtonDefaults.buttonColors(
            Color(0xFFEEBD0F)
         ),
         modifier = Modifier
            .fillMaxWidth()

      ) {
         Text("Inscription", color = Color.Black)
      }
   }
}
