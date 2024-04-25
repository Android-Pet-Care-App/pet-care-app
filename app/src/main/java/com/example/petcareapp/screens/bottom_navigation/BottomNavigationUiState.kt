package com.example.petcareapp.screens.bottom_navigation

data class BottomNavigationUiState (
    var navButtons: MutableMap<String, Boolean> = mutableMapOf(
        "profile" to false,
        "home" to false,
        "pets" to false,
    )
)