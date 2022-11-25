package com.example.sbertestapp.data

import com.example.sbertestapp.ui.Photo

data class PhotoModel(
    private val id: String,
    private val urls: Urls
) : Map<Photo> {

    override fun toMap(): Photo = Photo(urls.full)

    inner class Urls(
        val full: String
    )
}

interface Map<T> {

    fun toMap(): T
}
