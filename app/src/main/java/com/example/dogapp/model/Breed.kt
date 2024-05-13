package com.example.dogapp.model

data class Breed(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
