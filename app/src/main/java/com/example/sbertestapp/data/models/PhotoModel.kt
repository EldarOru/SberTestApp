package com.example.sbertestapp.data.models

import com.example.sbertestapp.ui.entities.Photo

data class PhotoModel(
    private val id: String,
    private val urls: Urls
) : Mapper<Photo> {

    override fun map(): Photo = Photo(id, urls.regular)

    inner class Urls(
        val regular: String
    )
}

