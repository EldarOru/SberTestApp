package com.example.sbertestapp.domain.interactors

import com.example.sbertestapp.ui.entities.State

interface Interactor<T> {

    suspend fun getDataState(): State<T>
}