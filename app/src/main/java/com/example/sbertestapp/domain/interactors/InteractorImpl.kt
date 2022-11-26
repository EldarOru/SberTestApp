package com.example.sbertestapp.domain.interactors

import com.example.sbertestapp.ui.handleerror.FailureHandler
import com.example.sbertestapp.data.models.PhotoModel
import com.example.sbertestapp.data.repositories.Repository
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.entities.State

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
