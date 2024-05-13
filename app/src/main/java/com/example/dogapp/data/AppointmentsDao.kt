package com.example.dogapp.data

import androidx.room.Dao
import androidx.room.Query
import com.example.dogapp.model.Appointment

@Dao
interface AppointmentsDao {
    @Query("SELECT * FROM Appointment")
    suspend fun getAppointments(): MutableList<Appointment>
}