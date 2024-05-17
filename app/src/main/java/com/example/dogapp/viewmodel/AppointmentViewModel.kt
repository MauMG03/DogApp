package com.example.dogapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.dogapp.model.Appointment
import com.example.dogapp.model.Breed
import com.example.dogapp.repository.AppointmentsRepository
import com.example.dogapp.model.Symptom
import com.example.dogapp.repository.BreedsRepository

class AppointmentViewModel(application: Application) :AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val breedsRepository = BreedsRepository(context)
    private val appointmentsRepository = AppointmentsRepository(context)

    private val _appointments = MutableLiveData<MutableList<Appointment>>()
    val appointments: LiveData<MutableList<Appointment>> get() = _appointments

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    // Para almacenar la lista de razas
    private val _breeds = MutableLiveData<MutableList<Breed>>()
    val breeds: LiveData<MutableList<Breed>> = _breeds

    private val _symptoms = MutableLiveData<MutableList<Symptom>>()
    val symptoms: LiveData<MutableList<Symptom>> = _symptoms

    fun getBreeds(){
        viewModelScope.launch {
            _progresState.value = true
            try {
                _breeds.value = breedsRepository.getBreeds()
                _progresState.value = false
            } catch (e: Exception){
                _progresState.value = false
            }
        }
    }

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image
    fun getImage(breed: String) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _image.value = breedsRepository.getImage(breed)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    private var initialized = false
    fun getSymptoms(): MutableLiveData<MutableList<Symptom>> {
        if (!initialized){

                val items = mutableListOf(
                    Symptom("Sintomas"),
                    Symptom("No come"),
                    Symptom("Fractura extremidad"),
                    Symptom("Tiene pulgas"),
                    Symptom("Tiene garrapatas"),
                    Symptom("Bota demasiado pelo"),
                )
                _symptoms.postValue(items)
                initialized = true


        }
        return _symptoms
    }

    fun insertAppointment(appointment: Appointment) {
    viewModelScope.launch {
        _progresState.value = true
        try {
            appointmentsRepository.insertAppointment(appointment)
            _progresState.value = false
        } catch (e: Exception) {
            _progresState.value = false
        }
    }
}

    fun getAppointments() {
        viewModelScope.launch {
            _appointments.value = appointmentsRepository.getAppointments()
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                appointmentsRepository.deleteAppointment(appointment)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

}