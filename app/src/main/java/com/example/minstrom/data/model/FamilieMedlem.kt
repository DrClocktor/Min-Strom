package com.example.minstrom.data.model
import com.google.firebase.Timestamp

//Dette er data "skabelonen"

data class FamilieMedlem(
    // val adminId: String = "", Hvordan skal det gøres?
    val familieNavn: String = "",
    val medlemmer: List<String>,
    val oprettet: Timestamp = Timestamp.now()
)
