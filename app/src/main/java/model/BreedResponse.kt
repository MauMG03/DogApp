package model

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("message")
    val message: Map<String, List<String>>,
    @SerializedName("status")
    val status: String
)
