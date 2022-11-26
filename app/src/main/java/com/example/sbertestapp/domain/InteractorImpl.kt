package com.example.sbertestapp.domain

import com.example.sbertestapp.data.FailureHandler
import com.example.sbertestapp.data.PhotoModel
import com.example.sbertestapp.data.Repository
import com.example.sbertestapp.ui.Photo
import com.example.sbertestapp.ui.State

//TODO ИСПРАВИТЬ
class InteractorImpl(
    private val repository: Repository<PhotoModel>,
    private val handler: FailureHandler
) : Interactor<List<Photo>> {

    override suspend fun getDataState(): State<List<Photo>> {
        return try {
            State.Loaded(repository.getListData().map { it.toMap() })
        } catch (e: Exception) {
            State.Error(handler.handle(e))
        }
    }
}
