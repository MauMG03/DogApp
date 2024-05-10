package com.example.dogapp.view.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Date(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val petName: String,
    val symptom: String,
    val petBreed: String,
    @SerializedName("petImage")
    val petImage: String
)
