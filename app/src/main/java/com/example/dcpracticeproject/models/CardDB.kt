package com.example.dcpracticeproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
class CardDB(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val name: String,
    val tier: String,
    val url: String,
    val set: String
)