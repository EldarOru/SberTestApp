package com.example.sbertestapp

import android.app.Application
import com.example.sbertestapp.di.AppComponent
import com.example.sbertestapp.di.AppModule
import com.example.sbertestapp.di.DaggerAppComponent

class App : Application(), ProvideInjection {

    override fun handle(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}

interface ProvideInjection{

    fun handle(): AppComponent
}