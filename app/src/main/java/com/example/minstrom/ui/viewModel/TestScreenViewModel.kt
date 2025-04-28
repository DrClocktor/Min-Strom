package com.example.minstrom.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.minstrom.data.model.FamilieMedlem
import com.example.minstrom.repository.FamilyRepository

class TestScreenViewModel: ViewModel() {
    val familyRepository = FamilyRepository()

    fun upload () {
        familyRepository.upload(
            FamilieMedlem(
            familieNavn = "",
            medlemmer = listOf(""),
        )
        )
    }

}