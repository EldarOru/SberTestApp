package com.example.sbertestapp.data

import android.content.Context

class ResourceManagerImpl(private val context: Context) : ResourceManager {

    override fun getString(stringResId: Int) = context.getString(stringResId)
}