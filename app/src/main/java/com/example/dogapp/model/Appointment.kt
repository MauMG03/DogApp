package com.example.dogapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String,
    val ownerName: String,
    val symptom: String,
    val petBreed: String,
    val phone: String,
    @SerializedName("petImage")
    val petImage: String
): Serializable
