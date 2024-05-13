package com.example.dogapp.view.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.dogapp.R
import com.example.dogapp.model.Symptom

class SymptomAdapter (
    context: Context,
    symptoms: MutableList<Symptom>
) : ArrayAdapter<Symptom>(context, 0, symptoms){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_symptom, parent, false)
        val symptom = getItem(position)
        val textView = view.findViewById<TextView>(R.id.tvSymptomName)
        textView.text = symptom?.name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}