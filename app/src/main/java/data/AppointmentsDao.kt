package data

import androidx.room.Dao
import androidx.room.Query
import model.Appointment

@Dao
interface AppointmentsDao {
    @Query("SELECT * FROM Appointment")
    suspend fun getAppointments(): MutableList<Appointment>
}