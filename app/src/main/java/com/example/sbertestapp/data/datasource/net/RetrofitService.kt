package com.example.sbertestapp.data.datasource.net

import com.example.sbertestapp.data.models.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("client_id") client_id: String = CLIENT_ID
    ): List<PhotoModel>

    companion object {
        //todo hide key
        const val CLIENT_ID = "kbdLo5fTjxscbcPFhGoKZSve1Jry7YK69UhT-KVDKFY"
    }
}