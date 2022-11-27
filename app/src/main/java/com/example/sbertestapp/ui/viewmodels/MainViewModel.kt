package com.example.sbertestapp.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.entities.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    interactor: Interactor<List<Photo>>,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : AbstractViewModel<Photo>(
    interactor, dispatcher
) {

    init {
        mutableLiveData.value = State.Default()
    }

    override fun fetchData() {
        viewModelScope.launch(dispatcher) {
            val loadingState = State.IsLoading<List<Photo>>()
            mutableLiveData.value = loadingState
            val result = interactor.getDataState()
            mutableLiveData.value = result
        }
    }

    override fun deleteItem(item: Photo) {
        if (mutableLiveData.value is State.Loaded<List<Photo>>) {
            val list = (mutableLiveData.value as State.Loaded<List<Photo>>).getData()
            mutableLiveData.value = State.Loaded(list.filter { it != item }.toList())
        }
    }
}