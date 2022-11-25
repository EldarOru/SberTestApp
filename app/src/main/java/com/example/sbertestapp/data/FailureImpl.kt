package com.example.sbertestapp.data

import androidx.annotation.StringRes
import com.example.sbertestapp.R

abstract class FailureImpl(private val resourceManager: ResourceManager) : Failure {

    @StringRes
    protected abstract fun getMessageResId(): Int

    override fun getMessage(): String = resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager) : FailureImpl(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager) : FailureImpl(resourceManager) {
    override fun getMessageResId() = R.string.service_unavailable
}

class GenericError(resourceManager: ResourceManager) : FailureImpl(resourceManager) {
    override fun getMessageResId() = R.string.generic_fail_message
}