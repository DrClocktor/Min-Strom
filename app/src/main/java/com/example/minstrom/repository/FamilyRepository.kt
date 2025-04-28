package com.example.minstrom.repository

import com.example.minstrom.data.model.FamilieMedlem
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//Her håndteres database kald
class FamilyRepository {
    fun upload(
        familieMedlem: FamilieMedlem,
    ) {
        val familieCollection = Firebase.firestore.collection("familier")

        familieCollection
            .add(familieMedlem)
            .addOnSuccessListener { documentReference ->
                println("Tilføjet med ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error: $e")
            }
    }
}