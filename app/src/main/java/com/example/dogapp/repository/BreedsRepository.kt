package com.example.dogapp.repository

import android.content.Context
import android.util.Log
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

    suspend fun getImage(breed: String): String{
            return withContext(Dispatchers.IO){
                try {
                    val response = apiService.getImage(breed)
                    Log.d("RESPONSE", response.message)
                    response.message
                } catch (e: Exception){
                    e.printStackTrace()
                    ""
                }
            }
        }


}