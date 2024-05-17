package com.example.dogapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentAppointmentDetailsBinding
import com.example.dogapp.model.Appointment
import com.example.dogapp.viewmodel.AppointmentViewModel

class AppointmentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentDetailsBinding
    private lateinit var receivedAppointment: Appointment
    private val appointmentViewModel: AppointmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
        dataInventory()
    }

    private fun controladores(){
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.action_appointmentDetailsFragment_to_homeFragment)
        }

        binding.fabEdit.setOnClickListener{
            //Insertar navegador al edit aquí
        }

        binding.fabDelete.setOnClickListener{
            deleteAppointment()
        }
    }

    private fun dataInventory(){
        val receivedBundle = arguments
        receivedAppointment = receivedBundle?.getSerializable("appointment") as Appointment
        Glide.with(binding.root.context).load(receivedAppointment.petImage).into(binding.ivDog)
        binding.tvName.text = "${receivedAppointment.petName}"
        binding.tvId.text = "#${receivedAppointment.id}"
        binding.tvBreed.text = "${receivedAppointment.petBreed}"
        binding.tvSymptom.text = "${receivedAppointment.symptom}"
        binding.tvOwner.text = "Propietario: ${receivedAppointment.ownerName}"
        binding.tvPhone.text = "Teléfono: ${receivedAppointment.phone}"
    }

    private fun deleteAppointment(){
        appointmentViewModel.deleteAppointment(receivedAppointment)
        findNavController().popBackStack()
    }

}