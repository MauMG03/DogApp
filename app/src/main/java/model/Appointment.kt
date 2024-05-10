package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val petName: Int,
    val symptoms: String,
    val petImage: String
)
