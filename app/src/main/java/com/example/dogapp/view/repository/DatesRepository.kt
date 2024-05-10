package com.example.dogapp.view.repository

import android.content.Context
import com.example.dogapp.view.data.DatesDB
import com.example.dogapp.view.data.DatesDao
import com.example.dogapp.view.model.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatesRepository(val context: Context) {
    private var datesDao: DatesDao = DatesDB.getDatabase(context).datesDao()

    suspend fun getDates():MutableList<Date>{
        return withContext(Dispatchers.IO){
            datesDao.getDates()
        }
    }
}