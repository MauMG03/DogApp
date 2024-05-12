package com.example.dogapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentCreateAppointmentBinding
import com.example.dogapp.view.adapter.SymptomAdapter
import com.example.dogapp.view.adapter.breedsAdapter
import viewmodel.AppointmentViewModel
import kotlin.math.log


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
        obeserverViewModel()
        observeSymptoms()
    }

    private fun obeserverViewModel(){
        observeBreeds()
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
    private fun controladores(){

    }
}