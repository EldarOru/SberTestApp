package com.example.sbertestapp.ui.handleerror

import com.example.sbertestapp.ui.appresources.ResourceManager

class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler {
    override fun handle(e: Exception): Failure = when (e) {
        is NoConnectionException -> NoConnection(resourceManager)
        is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
        else -> GenericError(resourceManager)
    }
}