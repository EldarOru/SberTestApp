package com.example.sbertestapp.data

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResId: Int): String
}