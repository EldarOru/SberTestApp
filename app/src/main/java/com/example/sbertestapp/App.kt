package com.example.sbertestapp

import android.app.Application
import com.example.sbertestapp.di.AppComponent
import com.example.sbertestapp.di.AppModule
import com.example.sbertestapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}