package repository

import android.content.Context
import data.AppointmentsDB
import data.AppointmentsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Appointment

class AppointmentsRepository(val context: Context) {
    private var appointmentsDao: AppointmentsDao = AppointmentsDB.getDatabase(context).appointmentsDao()

    suspend fun getAppointments():MutableList<Appointment>{
        return withContext(Dispatchers.IO){
            appointmentsDao.getAppointments()
        }
    }
}