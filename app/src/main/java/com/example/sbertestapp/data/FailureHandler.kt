package com.example.sbertestapp.data

interface FailureHandler {

    fun handle(e: Exception): Failure
}