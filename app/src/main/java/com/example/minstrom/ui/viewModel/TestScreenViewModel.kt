package com.example.minstrom.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.minstrom.data.model.FamilieMedlem
import com.example.minstrom.repository.FamilyRepository

class TestScreenViewModel: ViewModel() {
    private val familyRepository = FamilyRepository() //Hvorfor private?

    fun upload () { //Opretter et FamilieMedlem-objekt og beder famlilyRepository om at upload det til databasen
        familyRepository.addFamilyMember(
            FamilieMedlem(
            familieNavn = "Test Familie",
            medlemmer = listOf("test@test.dk", "tester@test.dk"),
        )
        )
    }

}