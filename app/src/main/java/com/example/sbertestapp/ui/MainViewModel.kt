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

class MainViewModel(
    private val interactor: Interactor<List<Photo>>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    private val mutableLiveData = MutableLiveData<State<List<Photo>>>()
    val liveData: LiveData<State<List<Photo>>>
    get() = mutableLiveData

    init {
        mutableLiveData.value = State.Default()
    }

    fun fetchData() {
        viewModelScope.launch(dispatcher) {
            val loadingState = State.IsLoading<List<Photo>>()
            mutableLiveData.value = loadingState
            val result = interactor.getDataState()
            mutableLiveData.value = result
        }
    }

    fun deleteData(photo: Photo) {
        if (mutableLiveData.value is State.Loaded<List<Photo>>) {
            val list = (mutableLiveData.value as State.Loaded<List<Photo>>).getData().toTypedArray()

            mutableLiveData.value = State.Loaded(list.filter { it != photo }.toList())
        }
    }
}