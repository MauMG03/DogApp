package com.example.dogapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapp.databinding.DateItemBinding
import com.example.dogapp.view.model.Date
import com.example.dogapp.view.viewholder.DatesViewHolder

class DatesAdapter(private val datesList:MutableList<Date>, private val navController: NavController):
    RecyclerView.Adapter<DatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesViewHolder {
        val binding = DateItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DatesViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return datesList.size
    }

    override fun onBindViewHolder(holder: DatesViewHolder, position: Int) {
        val date = datesList[position]
        holder.setDateItem(date)
    }
}