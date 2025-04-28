package com.example.minstrom.data.model

import com.google.firebase.Timestamp

data class FamilieMedlem(
    val familieNavn: String = "",
    val medlemmer: List<String>,
    val oprettet: Timestamp = Timestamp.now()
)
