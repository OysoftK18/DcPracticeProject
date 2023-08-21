package com.example.dcpracticeproject.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dcpracticeproject.models.CardDB
import com.example.dcpracticeproject.network.CardsDao
import kotlinx.coroutines.flow.Flow

interface CardsDBRepository {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(cardDB: CardDB)

    @Query("SELECT * FROM Cards")
    fun getAllCards(): Flow<List<CardDB>>

    @Query("SELECT * FROM cards WHERE id=:id LIMIT 1")
    fun getCard(id: Int): CardDB
}

class OfflineCardsDBRepository(private val cardsDao: CardsDao): CardsDBRepository{
    override suspend fun insertCard(cardDB: CardDB) = cardsDao.insertCard(cardDB)

    override fun getAllCards(): Flow<List<CardDB>> = cardsDao.getAllCards()

    override fun getCard(id: Int): CardDB = cardsDao.getCard(id)

}