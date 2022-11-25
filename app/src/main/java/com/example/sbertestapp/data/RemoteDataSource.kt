package com.example.sbertestapp.data

interface RemoteDataSource<T> {

    suspend fun getListData(): List<T>
}