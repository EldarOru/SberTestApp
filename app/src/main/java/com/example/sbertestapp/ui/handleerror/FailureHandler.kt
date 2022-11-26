package com.example.sbertestapp.ui.handleerror

interface FailureHandler {

    fun handle(e: Exception): Failure
}