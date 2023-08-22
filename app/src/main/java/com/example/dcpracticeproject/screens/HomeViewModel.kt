package com.example.dcpracticeproject.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dcpracticeproject.data.CardsRepository
import com.example.dcpracticeproject.models.Card
import com.example.dcpracticeproject.models.CardDB
import com.example.dcpracticeproject.models.toCardDb
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface FetchUiState{
    data class Success(val list: List<Card>): FetchUiState

    object Failed : FetchUiState

    object Loading: FetchUiState

    object Nothing: FetchUiState
}

class HomeViewModel(private val cardsRepository: CardsRepository) : ViewModel() {

    var fetchUiState: FetchUiState by mutableStateOf(FetchUiState.Nothing)
        private set

    val databaseUiState: StateFlow<DatabaseUiState> = cardsRepository.getAllCardsLocal().map { DatabaseUiState(it) }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000L), initialValue = DatabaseUiState()
    )


    fun onUpdateDataBaseClicked(){
        viewModelScope.launch {
            fetchUiState = FetchUiState.Loading
            fetchUiState = try {
                FetchUiState.Success(cardsRepository.getAllCards())
            }catch (e: IOException){
                FetchUiState.Failed
            }catch (e: HttpException){
                FetchUiState.Failed
            }

            when(fetchUiState){
                is FetchUiState.Success -> {
                    val cardList = cardsRepository.getAllCards()
                    for (card in cardList){
                        cardsRepository.insertCardLocal(cardDB = card.toCardDb())
                    }
                }
                else -> null
            }
        }
    }
}

data class DatabaseUiState(val cardList: List<CardDB> = listOf())