package com.example.sbertestapp.data

import java.net.UnknownHostException

class RemoteDataSourceImpl(private val retrofitService: RetrofitService): RemoteDataSource<PhotoModel> {

    override suspend fun getListData(): List<PhotoModel> {
        try {
            return retrofitService.getPhotos()
        } catch (e: Exception) {
            if (e is UnknownHostException) throw NoConnectionException()
            else throw ServiceUnavailableException()
        }
    }
}