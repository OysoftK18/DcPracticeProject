package com.example.dcpracticeproject

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dcpracticeproject.di.CardsApplication
import com.example.dcpracticeproject.screens.HomeViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val cardsRepository = cardsApplication().container.cardsRepository

            HomeViewModel(cardsRepository)
        }

    }


}

fun CreationExtras.cardsApplication(): CardsApplication =
        (this[AndroidViewModelFactory.APPLICATION_KEY] as CardsApplication)
