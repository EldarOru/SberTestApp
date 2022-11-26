package com.example.sbertestapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.ui.entities.Photo

class ViewModelFactory(
    private val interactor: Interactor<List<Photo>>
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            interactor = interactor
        ) as T
    }
}