package com.example.dogapp.view.viewholder

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
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

        bindingItem.dateItem.setOnClickListener {
            //navController.navigate()
        }
    }
}