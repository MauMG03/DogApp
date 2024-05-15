package com.example.dogapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentAppointmentDetailsBinding
import com.example.dogapp.model.Appointment

class AppointmentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentDetailsBinding
    private lateinit var receivedAppointment: Appointment

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
    }

    private fun dataInventory(){
        val receivedBundle = arguments
        receivedAppointment = receivedBundle?.getSerializable("appointment") as Appointment
        binding.tvTitle.text = "${receivedAppointment.petName}"

    }

}