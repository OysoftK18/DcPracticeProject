package com.example.dcpracticeproject.di

import android.content.Context
import com.example.dcpracticeproject.data.CardsDatabase
import com.example.dcpracticeproject.data.CardsRepository
import com.example.dcpracticeproject.data.DefaultCardsRepository
import com.example.dcpracticeproject.network.CardsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface DcContainer {

    val cardsRepository : CardsRepository
}

class DefaultDcContainer(context: Context) : DcContainer {

    val BASE_URL = "https://raw.githubusercontent.com/OysoftK18/DCompose/main/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CardsApiService by lazy {
        retrofit.create(CardsApiService::class.java)
    }

    override val cardsRepository: CardsRepository by lazy{
        DefaultCardsRepository(retrofitService, CardsDatabase.getDataBase(context).cardsDao())
    }

}