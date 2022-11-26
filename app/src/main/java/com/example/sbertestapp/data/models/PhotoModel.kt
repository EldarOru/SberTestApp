package com.example.sbertestapp.data.models

import com.example.sbertestapp.ui.entities.Photo

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
