package com.example.dogapp.view.repository

import android.content.Context
import com.example.dogapp.view.data.AppointmentsDB
import com.example.dogapp.view.data.AppointmentsDao
import com.example.dogapp.view.model.Appointment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppointmentsRepository(val context: Context) {
    private var appointmentsDao: AppointmentsDao = AppointmentsDB.getDatabase(context).appointmentsDao()

    suspend fun getAppointments():MutableList<Appointment>{
        return withContext(Dispatchers.IO){
            appointmentsDao.getAppointments()
        }
    }
}