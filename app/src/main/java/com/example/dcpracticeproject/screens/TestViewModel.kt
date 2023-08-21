package com.example.dcpracticeproject.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dcpracticeproject.data.CardsDBRepository
import com.example.dcpracticeproject.data.CardsRepository
import com.example.dcpracticeproject.di.CardsApplication
import com.example.dcpracticeproject.models.Card
import com.example.dcpracticeproject.models.toCardDb
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CardsUiState{

    data class Success(val cards: List<Card>): CardsUiState

    object Failed: CardsUiState

    object  Loading: CardsUiState
}

class TestViewModel(private val cardsRepository: CardsRepository, private val cardsDBRepository: CardsDBRepository) : ViewModel() {

    var cardsUiState: CardsUiState by mutableStateOf(CardsUiState.Loading)
        private set

    init{
        getAllCards()
    }

    private fun getAllCards(){
        viewModelScope.launch {
            cardsUiState = CardsUiState.Loading
            cardsUiState = try{
                CardsUiState.Success(cardsRepository.getAllCards())
            }catch (e: IOException){
                CardsUiState.Failed
            }catch (e: HttpException){
                CardsUiState.Failed
            }

            when(cardsUiState){
                is CardsUiState.Success -> {
                    val lista = cardsRepository.getAllCards()
                    for (card in lista){
                        cardsDBRepository.insertCard(cardDB = card.toCardDb())
                    }

                }
                else -> Log.e("Test", "Nothing")
            }
        }

    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CardsApplication)

                val cardsRepository = application.container.cardsRepository
                val cardsDBRepository = application.container.cardsDBRepository
                TestViewModel(cardsRepository, cardsDBRepository)
            }
        }
    }
}