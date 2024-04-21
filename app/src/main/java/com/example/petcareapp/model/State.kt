package com.example.petcareapp.model

data class State<out T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null
) {
    companion object {
        fun <T> success(data: T): State<T> = State(data = data)
        fun <T> error(message: String): State<T> = State(error = message)
        fun <T> loading(): State<T> = State(loading = true)
    }
}