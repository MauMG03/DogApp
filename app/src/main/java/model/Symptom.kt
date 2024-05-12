package model

data class Symptom (
    val name: String
) {
    override fun toString(): String {
        return name
    }
}