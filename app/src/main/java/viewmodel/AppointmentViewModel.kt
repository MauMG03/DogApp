package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Breed
import model.Symptom
import repository.BreedsRepository

class AppointmentViewModel(application: Application) :AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val breedsRepository = BreedsRepository(context)

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

}