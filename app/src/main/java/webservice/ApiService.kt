package webservice

import model.Breed
import model.BreedResponse
import retrofit2.http.GET
import utils.Constants

interface ApiService {
    @GET(Constants.ENDPOINT)
    suspend fun getBreeds(): BreedResponse

}