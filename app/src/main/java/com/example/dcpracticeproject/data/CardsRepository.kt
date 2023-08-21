package com.example.dcpracticeproject.data

import com.example.dcpracticeproject.models.Card
import com.example.dcpracticeproject.network.CardsApiService
import retrofit2.http.GET
import retrofit2.http.Query

interface CardsRepository {

    @GET("Heroes.json")
    suspend fun getAllCards(): List<Card>


}

class DefaultCardsRepository(private val cardsApiService: CardsApiService) : CardsRepository{

    override suspend fun getAllCards(): List<Card> = cardsApiService.getAllCards()

}