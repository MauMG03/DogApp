package com.example.dogapp.webservice

import com.example.dogapp.model.BreedImageResponse
import com.example.dogapp.model.BreedResponse
import retrofit2.http.GET
import com.example.dogapp.utils.Constants
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.ENDPOINT)
    suspend fun getBreeds(): BreedResponse

    @GET(Constants.ENDPOINT_IMAGE)
    suspend fun getImage(@Path("chosen") chosen: String): BreedImageResponse

}