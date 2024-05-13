package com.example.dogapp.view.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dogapp.databinding.FragmentCreateAppointmentBinding
import com.example.dogapp.view.adapter.SymptomAdapter
import com.example.dogapp.view.adapter.breedsAdapter
import com.example.dogapp.viewmodel.AppointmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.dogapp.R
import com.example.dogapp.model.Appointment


class CreateAppointmentFragment : Fragment() {
    private lateinit var binding: FragmentCreateAppointmentBinding
    private val appointmentViewModel: AppointmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create_appointment, container, false)
        binding = FragmentCreateAppointmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        controllers()
    }

    private fun observerViewModel(){
        observeBreeds()
        observeSymptoms()
    }

    private fun observeBreeds(){
        appointmentViewModel.getBreeds()
        appointmentViewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            Log.d("BREEDS", breeds.toString())
            val breedsAdapter = breedsAdapter(requireContext(), breeds)
            binding.actvBreed.setAdapter(breedsAdapter)
            binding.actvBreed.threshold = 2

            //I believe this disallow the show of the dropdown not sure how but this is bad probably better ways of doing this.
            binding.actvBreed.setOnTouchListener { v, event ->
                if (binding.actvBreed.isFocused && binding.actvBreed.text.isEmpty()) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        v.performClick() // Call performClick when a click is detected
                    }
                    true
                } else {
                    false
                }
            }
            //I hate this
        }
    }

    private fun observeSymptoms(){
        appointmentViewModel.getSymptoms()
        appointmentViewModel.symptoms.observe(viewLifecycleOwner) { symptoms ->
            Log.d("SYMPTOMS", symptoms.toString())
            val symptomsAdapter = SymptomAdapter(requireContext(), symptoms)
            binding.spSymptoms.setAdapter(symptomsAdapter)
        }

    }


    private fun controllers(){
        validateFields()
        binding.btnSaveAppointment.setOnClickListener {
            if (areFieldsFilled() && binding.spSymptoms.selectedItemPosition != 0 ) {
                val petName = binding.etPetName.text.toString()
                val breed = binding.actvBreed.text.toString()
                val ownerName = binding.etOwnerName.text.toString()
                val symptom = binding.spSymptoms.selectedItem.toString()
                val phone = binding.etPhone.text.toString()
                appointmentViewModel.getImage(breed)
                appointmentViewModel.image.observe(viewLifecycleOwner) { image ->
                    val appointment = Appointment(petName = petName, ownerName = ownerName, petBreed = breed, symptom = symptom, phone = phone, petImage = image)
                    appointmentViewModel.insertAppointment(appointment)
                    Log.d("APPOINTMENT", appointment.toString())
                    findNavController().popBackStack()
                }
            } else {
                val selectedItem = binding.spSymptoms.selectedItem.toString()
                if (selectedItem == "Sintomas") {
                    Snackbar.make(binding.root, "Selecciona un síntoma", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.action_createAppointmentFragment_to_homeFragment)
        }
    }

    private fun validateFields(){
        binding.etPetName.addTextChangedListener(TextWatcher)
        binding.actvBreed.addTextChangedListener(TextWatcher)
        binding.etOwnerName.addTextChangedListener(TextWatcher)
        binding.etPhone.addTextChangedListener(TextWatcher)

        binding.btnSaveAppointment.isEnabled = areFieldsFilled()
    }
    fun areFieldsFilled(): Boolean {
        val petName = binding.etPetName.text ?: ""
        val breed = binding.actvBreed.text ?: ""
        val ownerName = binding.etOwnerName.text ?: ""
        val phone = binding.etPhone.text ?: ""
        return petName.isNotEmpty() && breed.isNotEmpty() && ownerName.isNotEmpty() && phone.isNotEmpty()
    }

    private val TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: android.text.Editable?) {
            binding.btnSaveAppointment.isEnabled = areFieldsFilled()
        }
    }



}