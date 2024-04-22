package com.example.petcareapp.screens.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.petcareapp.HOME_SCREEN
import com.example.petcareapp.SIGN_UP_SCREEN
import com.example.petcareapp.common.snackbar.SnackbarManager
import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.petcareapp.R.string as AppText
import com.example.petcareapp.common.ext.isValidEmail
import com.example.petcareapp.common.ext.isValidPassword
import com.example.petcareapp.common.ext.passwordMatches
import com.example.petcareapp.model.State
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : PetCareAppViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        // Launch a coroutine to call signUpUser
        viewModelScope.launch {
            try { // Sign up the user
                accountService.signUpUser(email, password).collect { state ->
                    if (state.loading) {
                        // Handle loading state if needed
                        // Example: show a progress indicator
                        /**
                         * TODO: Implement loading indicator
                         */
                        Log.d("SignUp", "Loading...")
                    } else if (state.data != null) {
                        /**
                         * TODO: Navigate to the home screen upon successful sign in
                         */
                        openAndPopUp(HOME_SCREEN, SIGN_UP_SCREEN)
                        Log.e("SignUp", "Successfully signed up")
                    } else if (state.error != null) {
                        val errorMessage = state.error ?: "Unknown error"
                        Log.e("SignUp", "Error: $errorMessage")
                    }
                }
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                Log.d("SignUp", "Sign up failed: $errorMessage", e)
            }

            try { // Authenticate the user
                accountService.authenticate(email, password)
                Log.d("SignUp", "Successfully authenticated: ${accountService.currentUserId}")
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                Log.e("SignUp", "Authentication failed: $errorMessage", e)
            }
        }

    }
}
