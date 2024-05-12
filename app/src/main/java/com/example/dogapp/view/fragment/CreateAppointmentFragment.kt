package com.example.dogapp.view.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dogapp.databinding.FragmentCreateAppointmentBinding
import com.example.dogapp.view.adapter.SymptomAdapter
import com.example.dogapp.view.adapter.breedsAdapter
import viewmodel.AppointmentViewModel


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