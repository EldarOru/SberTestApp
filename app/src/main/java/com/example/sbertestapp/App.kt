package com.example.sbertestapp

import android.app.Application
import com.example.sbertestapp.di.AppComponent
import dagger.internal.DaggerCollections

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

    }
}