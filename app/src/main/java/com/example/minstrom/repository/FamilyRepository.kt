package com.example.minstrom.repository

import com.example.minstrom.data.model.FamilieMedlem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//Her håndteres databasen (Slags mellemmand mellem database og app)

class FamilyRepository {
    fun addFamilyMember(familieMedlem: FamilieMedlem) {
        val familieCollection = Firebase.firestore.collection("familier") //Her defineres collection arbejdes med

        familieCollection //Her tilføjes data
            .add(familieMedlem) //Tilføjer nyt dokument til collection (Indeholder ikke data endnu)
            .addOnSuccessListener { documentReference ->
                println("Tilføjet med ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error: $e")
            }
    }
}