package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Breed
import repository.BreedsRepository

class AppointmentViewModel(application: Application) :AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val breedsRepository = BreedsRepository(context)

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    // Para almacenar la lista de razas
    private val _breeds = MutableLiveData<MutableList<Breed>>()
    val breeds: LiveData<MutableList<Breed>> = _breeds

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

}