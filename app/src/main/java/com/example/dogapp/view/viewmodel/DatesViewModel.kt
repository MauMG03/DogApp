package com.example.dogapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogapp.view.model.Date
import com.example.dogapp.view.repository.DatesRepository
import kotlinx.coroutines.launch

class DatesViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val datesRepository = DatesRepository(context)

    private val _dates = MutableLiveData<MutableList<Date>>()
    val dates: LiveData<MutableList<Date>> get() = _dates

    fun getDates() {
        viewModelScope.launch {
            _dates.value = datesRepository.getDates()
        }
    }
}