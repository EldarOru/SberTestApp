package com.example.sbertestapp.data.repositories

interface Repository<T> {

    suspend fun getListData(): List<T>
}