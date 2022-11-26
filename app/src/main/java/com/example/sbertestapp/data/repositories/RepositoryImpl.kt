package com.example.sbertestapp.data.repositories

import com.example.sbertestapp.data.datasource.net.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl<T>(private val remoteDataSource: RemoteDataSource<T>) :
    Repository<T> {

    override suspend fun getListData(): List<T> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getListData()
        }
}