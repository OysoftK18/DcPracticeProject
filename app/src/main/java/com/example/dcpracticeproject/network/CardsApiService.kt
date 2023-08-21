package com.example.dcpracticeproject.network

import com.example.dcpracticeproject.models.Card
import retrofit2.http.GET
import retrofit2.http.Query

interface CardsApiService {

    @GET("Heroes.json")
    suspend fun getAllCards(): List<Card>


}