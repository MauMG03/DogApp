package com.example.dogapp.model

data class Symptom (
    val name: String
) {
    override fun toString(): String {
        return name
    }
}