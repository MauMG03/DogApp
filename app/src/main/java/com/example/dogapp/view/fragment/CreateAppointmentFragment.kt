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
    }

    private fun obeserverViewModel(){
        observeBreeds()
    }

    private fun observeBreeds(){
        appointmentViewModel.getBreeds()
        appointmentViewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            Log.d("BREEDS", breeds.toString())
        }
    }
    private fun controladores(){

    }
}