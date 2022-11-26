package com.example.sbertestapp.di

import com.example.sbertestapp.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, MainModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}