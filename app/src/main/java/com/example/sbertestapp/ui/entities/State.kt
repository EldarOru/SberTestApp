package com.example.sbertestapp.ui.entities

import com.example.sbertestapp.ui.handleerror.Failure

sealed class State<T> {

    class IsLoading<T> : State<T>()

    class Loaded<T>(private val data: T) : State<T>() {

        fun getData() = data
    }

    class Error<T>(private val failure: Failure) : State<T>() {

        fun getErrorMessage() = failure.getMessage()
    }

    class Default<T> : State<T>()
}