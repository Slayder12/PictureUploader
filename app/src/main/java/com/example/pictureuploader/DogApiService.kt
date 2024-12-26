package com.example.pictureuploader

import com.example.pictureuploader.models.ApiData
import retrofit2.http.GET

interface DogApiService {
    @GET("woof.json?ref=apilist.fun")
    suspend fun getRandomDog(): ApiData
}