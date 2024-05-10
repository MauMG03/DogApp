package com.example.dogapp.view.viewholder

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapp.databinding.DateItemBinding
import com.example.dogapp.view.model.Date

class DatesViewHolder(binding: DateItemBinding, navController: NavController) :
    RecyclerView.ViewHolder(binding.root){
    val bindingItem = binding
    val navController = navController

    fun setDateItem(date: Date) {
        bindingItem.petName.text = date.petName
        bindingItem.symptom.text = date.symptom
        bindingItem.id.text = "# ${date.id}"
        Glide.with(bindingItem.root.context).load(date.petImage).into(bindingItem.imagePet)

        bindingItem.dateItem.setOnClickListener {
            Toast.makeText(bindingItem.root.context, "Navigate to appoiment details", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("date", date)
            //navController.navigate(tofargment, bundle)
        }
    }
}