package com.example.dogapp.view.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogapp.view.model.Date
import com.example.dogapp.view.utils.Constants.NAME_BD

@Database(entities = [Date::class], version = 1)
abstract class DatesDB: RoomDatabase() {
    abstract fun datesDao(): DatesDao

    companion object{
        fun getDatabase(context: Context): DatesDB {
            return Room.databaseBuilder(
                context.applicationContext,
                DatesDB::class.java,
                NAME_BD
            ).build()
        }
    }
}