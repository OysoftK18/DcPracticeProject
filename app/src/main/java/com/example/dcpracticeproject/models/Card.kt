package com.example.dcpracticeproject.models

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val name: String,
    val tier: String,
    val url: String,
    val set: String
)

fun Card.toCardDb(): CardDB{
    return CardDB(
        name = this.name,
        url = this.url,
        tier = this.tier,
        set = this.set
    )
}
