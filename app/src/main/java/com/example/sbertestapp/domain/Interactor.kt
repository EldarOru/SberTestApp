package com.example.sbertestapp.domain

import com.example.sbertestapp.ui.State

interface Interactor<T> {

    suspend fun getDataState(): State<T>
}