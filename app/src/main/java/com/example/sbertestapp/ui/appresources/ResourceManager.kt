package com.example.sbertestapp.ui.appresources

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResId: Int): String
}