package com.example.dcpracticeproject.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dcpracticeproject.models.CardDB
import kotlinx.coroutines.flow.Flow

@Dao
interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(cardDB: CardDB)

    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardDB>>

    @Query("SELECT * FROM cards WHERE id=:id LIMIT 1")
    fun getCard(id: Int): CardDB

    @Query("SELECT * FROM cards WHERE tier=:tier")
    suspend fun getCardsLocalTier(tier: Int): List<CardDB>
}