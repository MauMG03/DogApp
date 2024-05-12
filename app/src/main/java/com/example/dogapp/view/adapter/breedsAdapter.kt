package com.example.dogapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.navigation.NavController
import com.example.dogapp.R
import model.Breed

class breedsAdapter(
    context: Context,
    breeds: MutableList<Breed>,

) :ArrayAdapter<Breed>(context, 0, breeds) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_breed, parent, false)
        val breed = getItem(position)
        val textView = view.findViewById<TextView>(R.id.tvBreedName)
        textView.text = breed?.name
        return view
    }
}