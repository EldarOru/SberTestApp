package com.example.sbertestapp.data

interface Repository<T> {

    suspend fun getListData(): List<T>
}