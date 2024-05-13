package com.example.dogapp.repository

import android.content.Context
import com.example.dogapp.data.AppointmentsDB
import com.example.dogapp.data.AppointmentsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.dogapp.model.Appointment

class AppointmentsRepository(val context: Context) {
    private var appointmentsDao: AppointmentsDao = AppointmentsDB.getDatabase(context).appointmentsDao()

    suspend fun getAppointments():MutableList<Appointment>{
        return withContext(Dispatchers.IO){
            appointmentsDao.getAppointments()
        }
    }
}