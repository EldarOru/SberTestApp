package com.example.sbertestapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sbertestapp.domain.Interactor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel<T>(
    interactor: Interactor<List<T>>,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
): AbstractViewModel<T>(
    interactor, dispatcher
){

    override fun fetchData() {
        viewModelScope.launch(dispatcher) {
            val loadingState = State.IsLoading<List<T>>()
            mutableLiveData.value = loadingState
            val result = interactor.getDataState()
            mutableLiveData.value = result
        }
    }

    override fun init() {
        mutableLiveData.value = State.Default()
    }

    override fun deleteItem(item: T) {
        if (mutableLiveData.value is State.Loaded<List<T>>) {
            val list = (mutableLiveData.value as State.Loaded<List<T>>).getData()

            mutableLiveData.value = State.Loaded(list.filter { it != item }.toList())
        }
    }
}