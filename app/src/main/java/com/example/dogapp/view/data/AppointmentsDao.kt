package com.example.dogapp.view.data

import androidx.room.Dao
import androidx.room.Query
import com.example.dogapp.view.model.Appointment

@Dao
interface AppointmentsDao {
    @Query("SELECT * FROM Appointment")
    suspend fun getAppointments(): MutableList<Appointment>
}