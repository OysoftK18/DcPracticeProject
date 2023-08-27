package com.example.dcpracticeproject.data

import android.util.Log
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

     fun getPlayersRoast(players: Int) {
         val combinations: MutableList<MutableList<Int>> = mutableListOf()
        repeat(players) {
            combinations.add(getCombinationHeroes())
        }
        Log.d("Heroes", combinations.toString())

        //TODO get the heroes from the database
        for (player in combinations) {
            Log.d("Heroes", getHeroesFromDatabase(player).toString())
        }
    }

    private fun getHeroesFromDatabase(list: List<Int>): List<String> {
        val listOfHeroes: MutableList<String> = mutableListOf()
        list.forEach {
            val tierList = repository.getCardsLocalTier(it)

            val heroNumber = random.nextInt(1, tierList.size+1)
            listOfHeroes.add(tierList[heroNumber].name)
        }
        return listOfHeroes.toList()
    }
}