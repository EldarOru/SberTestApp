package com.example.sbertestapp.di

import android.content.Context
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainViewModelFactory(interactor: Interactor<List<Photo>>): ViewModelFactory {
        return ViewModelFactory(interactor)
    }
}