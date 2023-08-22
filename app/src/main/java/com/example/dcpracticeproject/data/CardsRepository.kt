package com.example.dcpracticeproject.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dcpracticeproject.models.Card
import com.example.dcpracticeproject.models.CardDB
import com.example.dcpracticeproject.network.CardsApiService
import com.example.dcpracticeproject.network.CardsDao
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CardsRepository {

    @GET("Heroes.json")
    suspend fun getAllCards(): List<Card>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardLocal(cardDB: CardDB)

    @Query("SELECT * FROM Cards")
    fun getAllCardsLocal(): Flow<List<CardDB>>

    @Query("SELECT * FROM cards WHERE id=:id LIMIT 1")
    fun getCardLocal(id: Int): CardDB


}

class DefaultCardsRepository(private val cardsApiService: CardsApiService, private val cardsDao: CardsDao) : CardsRepository{

    override suspend fun getAllCards(): List<Card> = cardsApiService.getAllCards()
    override suspend fun insertCardLocal(cardDB: CardDB) = cardsDao.insertCard(cardDB)

    override fun getAllCardsLocal(): Flow<List<CardDB>> = cardsDao.getAllCards()

    override fun getCardLocal(id: Int): CardDB = cardsDao.getCard(id)

}