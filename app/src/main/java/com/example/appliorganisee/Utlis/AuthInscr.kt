package com.example.appliorganisee.Utlis



import android.util.Log
import androidx.compose.runtime.MutableState
import com.google.firebase.auth.FirebaseAuth

fun authentification(
    email: String,
    password: String,
    auth: FirebaseAuth,
    deconnecte: MutableState<Boolean>
) {
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            deconnecte.value = false
            Log.d("JUAN", "signInWithEmail:success")
        } else {
            // If sign in fails, display a message to the user.
            Log.w("JUAN", "signInWithEmail:failure", task.exception)

        }
    }
}

fun inscription(email: String, password: String, auth: FirebaseAuth, deconnecte: MutableState<Boolean>){
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            deconnecte.value = false
            Log.d("JUAN", "createUserWithEmail:success")
            //val user = auth.currentUser
        } else {
            // If sign in fails, display a message to the user.
            Log.w("JUAN", "createUserWithEmail:failure", task.exception)
        }
    }
}