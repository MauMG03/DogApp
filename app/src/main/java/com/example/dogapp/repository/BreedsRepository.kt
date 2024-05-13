package com.example.dogapp.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.dogapp.model.Breed
import com.example.dogapp.webservice.ApiService
import com.example.dogapp.webservice.ApiUtils

class BreedsRepository(val context:Context) {
    private var apiService: ApiService = ApiUtils.getApiService()

    suspend fun getBreeds(): MutableList<Breed>{
        return withContext(Dispatchers.IO){
            try {
                val response = apiService.getBreeds()
                val breeds: MutableList<Breed> = mutableListOf()
                response.message.forEach { breedEntry ->
                    breeds.add(Breed(breedEntry.key))
                }
                breeds
            } catch (e: Exception){
                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}