package com.example.sbertestapp.data.datasource.net

interface RemoteDataSource<T> {

    suspend fun getListData(): List<T>
}