package com.example.dcpracticeproject.data

import android.util.Log
import com.example.dcpracticeproject.models.CardDB
import kotlin.random.Random

class RoastGenerator(val repository: CardsRepository) {

    private val random = Random.Default
    private fun getCombinationHeroes(): MutableList<Int> {
        var num1: Int
        var num2: Int
        var sum: Int
        do {
            num1 = random.nextInt(1, 7)
            num2 = random.nextInt(1, 7)
            sum = num1 + num2
        } while (sum != 5 && sum != 6)
        return mutableListOf(num1, num2)
    }

    suspend fun getPlayersRoast(players: Int): List<List<CardDB>> {
        val combinations: MutableList<MutableList<Int>> = mutableListOf()
        repeat(players) {
            combinations.add(getCombinationHeroes())
        }
        Log.d("Heroes", combinations.toString())

        //TODO get the heroes from the database
        val heroes : MutableList<List<CardDB>> = mutableListOf()
        for (player in combinations) {
            heroes.add(getHeroesFromDatabase(player))
        }
        return heroes
    }

    private suspend fun getHeroesFromDatabase(list: List<Int>): List<CardDB> {
        val listOfHeroes: MutableList<CardDB> = mutableListOf()
        list.forEach {
            val tierList = repository.getCardsLocalTier(it)

            val heroNumber = random.nextInt(1, tierList.size + 1)
            listOfHeroes.add(tierList[heroNumber])
        }
        return listOfHeroes.toList()
    }
}