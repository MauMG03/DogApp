package com.example.dogapp.webservice

import com.example.dogapp.model.BreedResponse
import retrofit2.http.GET
import com.example.dogapp.utils.Constants

interface ApiService {
    @GET(Constants.ENDPOINT)
    suspend fun getBreeds(): BreedResponse

}