package com.example.dcpracticeproject.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dcpracticeproject.models.CardDB
import com.example.dcpracticeproject.network.CardsDao

@Database(entities = [CardDB::class], version = 1, exportSchema = false)
abstract class CardsDatabase : RoomDatabase() {

    abstract fun cardsDao(): CardsDao

        companion object{

            @Volatile
            var INSTANCE : CardsDatabase? = null

            fun getDataBase(context: Context): CardsDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context = context,
                    klass = CardsDatabase::class.java,
                    name = "cards_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                        Log.d("Tag", it.toString())
                    }
            }
        }
    }
}