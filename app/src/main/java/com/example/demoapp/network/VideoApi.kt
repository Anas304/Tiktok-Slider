package com.example.demoapp.network

import com.example.demoapp.network.model.Video
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VideoApi {
    @GET("VideoDummyApi/dummy_data.json")
    fun getData(): Deferred<List<Video>>
}