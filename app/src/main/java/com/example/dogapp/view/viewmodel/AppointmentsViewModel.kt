package com.example.dogapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogapp.view.model.Appointment
import com.example.dogapp.view.repository.AppointmentsRepository
import kotlinx.coroutines.launch

class AppointmentsViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val appointmentsRepository = AppointmentsRepository(context)

    private val _appointments = MutableLiveData<MutableList<Appointment>>()
    val appointments: LiveData<MutableList<Appointment>> get() = _appointments

    fun getAppointments() {
        viewModelScope.launch {
            _appointments.value = appointmentsRepository.getAppointments()
        }
    }
}