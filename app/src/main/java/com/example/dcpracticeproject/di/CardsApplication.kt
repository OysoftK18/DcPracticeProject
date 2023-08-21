package com.example.dcpracticeproject.di

import android.app.Application

class CardsApplication : Application() {

    lateinit var container: DcContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultDcContainer(this)
    }
}