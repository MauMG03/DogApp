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
import com.example.dogapp.R
import com.example.dogapp.model.Appointment
import com.example.dogapp.viewmodel.AppointmentViewModel
import com.example.dogapp.databinding.FragmentUpdateAppointmentBinding
import com.example.dogapp.view.adapter.breedsAdapter

class UpdateAppointmentFragment : Fragment() {
    private lateinit var binding: FragmentUpdateAppointmentBinding
    private val appointmentViewModel: AppointmentViewModel by viewModels()
    private lateinit var receivedAppointment: Appointment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateAppointmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataAppointment()
        observeBreeds()
        controladores()
    }

    private fun observeBreeds(){
        appointmentViewModel.getBreeds()
        appointmentViewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            Log.d("BREEDS", breeds.toString())
            val breedsAdapter = breedsAdapter(requireContext(), breeds)
            binding.actvBreed.setAdapter(breedsAdapter)
            binding.actvBreed.threshold = 2

            binding.actvBreed.setOnTouchListener { v, event ->
                if (binding.actvBreed.isFocused && binding.actvBreed.text.isEmpty()) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        v.performClick()
                    }
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun controladores(){
        validateFields()
        binding.btnUpdateAppointment.setOnClickListener{
            updateAppointment()
        }
        binding.ivBack.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("appointment", receivedAppointment)
            findNavController().navigate(R.id.action_updateAppointment_to_detailsFragment, bundle)
        }
    }

    private fun validateFields(){
        binding.etPetName.addTextChangedListener(TextWatcher)
        binding.actvBreed.addTextChangedListener(TextWatcher)
        binding.etOwnerName.addTextChangedListener(TextWatcher)
        binding.etPhone.addTextChangedListener(TextWatcher)

        binding.btnUpdateAppointment.isEnabled = areFieldsFilled()
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
            binding.btnUpdateAppointment.isEnabled = areFieldsFilled()
        }
    }

    private fun dataAppointment(){
        val receivedBundle = arguments
        receivedAppointment = receivedBundle?.getSerializable("dataAppointment") as Appointment
        binding.etPetName.setText(receivedAppointment.petName)
        binding.actvBreed.setText(receivedAppointment.petBreed)
        binding.etOwnerName.setText(receivedAppointment.ownerName)
        binding.etPhone.setText(receivedAppointment.phone)
    }

    private fun updateAppointment() {
        val receivedBundle = arguments
        receivedAppointment = receivedBundle?.getSerializable("dataAppointment") as Appointment
        val originalBreed = receivedAppointment.petBreed

        val petName = binding.etPetName.text.toString()
        val petBreed = binding.actvBreed.text.toString()
        val ownerName = binding.etOwnerName.text.toString()
        val ownerPhone = binding.etPhone.text.toString()

        var appointment = Appointment(receivedAppointment.id, petName, ownerName, receivedAppointment.symptom, petBreed, ownerPhone, receivedAppointment.petImage)

        if (originalBreed != petBreed) {
            // Solicitar nueva imagen de la API para la nueva raza
            appointmentViewModel.getImage(petBreed)

            // Observar el LiveData de la imagen
            appointmentViewModel.image.observe(viewLifecycleOwner) { image ->
                appointment = Appointment(receivedAppointment.id, petName, ownerName, receivedAppointment.symptom, petBreed, ownerPhone, image)
                appointmentViewModel.updateAppointment(appointment)
                findNavController().navigate(R.id.action_updateAppointment_to_homeFragment)
            }
        } else {
            // Si la raza no cambia, actualizar la cita inmediatamente
            appointmentViewModel.updateAppointment(appointment)
            findNavController().navigate(R.id.action_updateAppointment_to_homeFragment)
        }
    }

}