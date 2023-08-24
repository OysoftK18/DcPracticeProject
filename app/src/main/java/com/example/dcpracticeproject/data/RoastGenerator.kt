package com.example.dcpracticeproject.data

import android.util.Log
import kotlin.random.Random

object RoastGenerator {
    private fun getCombinationHeroes(): MutableList<Int>{
        val random =  Random.Default
        var num1: Int
        var num2: Int
        var sum: Int
        do {
            num1= random.nextInt(1,7)
            num2 = random.nextInt(1,7)
            sum = num1 + num2
        }while(sum != 5 && sum != 6 )
        return mutableListOf(num1, num2)
    }

    fun getPlayersRoast(players: Int){
        var combinations: MutableList<MutableList<Int>> = mutableListOf()
        repeat(players){
            combinations.add(getCombinationHeroes())
        }
        Log.d("Heroes", combinations.toString())

        //TODO get the heroes from the database
    }
}