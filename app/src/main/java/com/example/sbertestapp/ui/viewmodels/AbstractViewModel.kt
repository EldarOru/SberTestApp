package com.example.sbertestapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.ui.entities.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class AbstractViewModel<T>(
    protected val interactor: Interactor<List<T>>,
    protected val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    protected val mutableLiveData = MutableLiveData<State<List<T>>>()
    val liveData: LiveData<State<List<T>>>
        get() = mutableLiveData

    abstract fun fetchData()

    abstract fun deleteItem(item: T)

}