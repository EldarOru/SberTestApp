package com.example.sbertestapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl<T>(private val remoteDataSource: RemoteDataSource<T>): Repository<T> {

    override suspend fun getListData(): List<T> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getListData()
        }
}