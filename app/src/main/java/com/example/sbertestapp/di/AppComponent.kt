package com.example.sbertestapp.di

import dagger.Component

@Component(modules = [AppModule::class, MainModule::class])
interface AppComponent {
}