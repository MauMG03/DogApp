package com.example.dogapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapp.databinding.AppointmentItemBinding
import com.example.dogapp.view.viewholder.AppointmentsViewHolder
import model.Appointment

class AppointmentsAdapter(private val appointmentsList:MutableList<Appointment>, private val navController: NavController):
    RecyclerView.Adapter<AppointmentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val binding = AppointmentItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AppointmentsViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.setAppointmentItem(appointment)
    }
}