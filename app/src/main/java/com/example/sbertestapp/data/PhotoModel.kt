package com.example.sbertestapp.data

import com.example.sbertestapp.ui.Photo

data class PhotoModel(
    private val id: String,
    private val urls: Urls
) : Map<Photo> {

    override fun toMap(): Photo = Photo(id, urls.regular)

    inner class Urls(
        val regular: String
    )
}

interface Map<T> {

    fun toMap(): T
}
