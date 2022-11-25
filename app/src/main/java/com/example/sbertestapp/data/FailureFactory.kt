package com.example.sbertestapp.data

class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler {
    override fun handle(e: Exception): Failure = when (e) {
        is NoConnectionException -> NoConnection(resourceManager)
        is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
        else -> GenericError(resourceManager)
    }
}