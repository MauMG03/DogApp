package com.example.dogapp.view.viewholder

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapp.databinding.AppointmentItemBinding
import model.Appointment

class AppointmentsViewHolder(binding: AppointmentItemBinding, navController: NavController) :
    RecyclerView.ViewHolder(binding.root){
    val bindingItem = binding
    val navController = navController

    fun setAppointmentItem(appointment: Appointment) {
        bindingItem.petName.text = appointment.petName
        bindingItem.symptom.text = appointment.symptom
        bindingItem.id.text = "# ${appointment.id}"
        Glide.with(bindingItem.root.context).load(appointment.petImage).into(bindingItem.imagePet)

        bindingItem.dateItem.setOnClickListener {
            Toast.makeText(bindingItem.root.context, "Navigate to appoiment details", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("appointment", appointment)
            //navController.navigate(tofargment, bundle)
        }
    }
}