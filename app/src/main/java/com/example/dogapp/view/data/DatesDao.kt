package com.example.dogapp.view.data

import androidx.room.Dao
import androidx.room.Query
import com.example.dogapp.view.model.Date

@Dao
interface DatesDao {
    @Query("SELECT * FROM Date")
    suspend fun getDates(): MutableList<Date>
}