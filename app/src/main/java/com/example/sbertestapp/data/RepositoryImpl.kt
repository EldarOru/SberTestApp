package com.example.sbertestapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val remoteDataSource: RemoteDataSource<PhotoModel>): Repository<PhotoModel> {

    override suspend fun getListData(): List<PhotoModel> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getListData()
        }
}