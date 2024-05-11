package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Appointment
import utils.Constants.NAME_DB

@Database(entities = [Appointment::class], version = 1)
abstract class AppointmentsDB: RoomDatabase() {
    abstract fun appointmentsDao(): AppointmentsDao

    companion object{
        fun getDatabase(context: Context): AppointmentsDB {
            return Room.databaseBuilder(
                context.applicationContext,
                AppointmentsDB::class.java,
                NAME_DB
            ).build()
        }
    }
}