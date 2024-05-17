package com.example.dogapp.view.fragment

import android.content.Context
import android.util.AttributeSet
import android.widget.AutoCompleteTextView

class CustomAutoCompleteTextView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatAutoCompleteTextView(context, attrs) {
    override fun performClick(): Boolean {
        return false
    }
}