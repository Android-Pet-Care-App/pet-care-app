package com.example.petcareapp.model.service

import com.example.petcareapp.model.State
import com.example.petcareapp.model.User
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
//    suspend fun linkAccount(email: String, password: String): Result<Unit>
    suspend fun deleteAccount()
    suspend fun signOut()
    suspend fun signUpUser(email: String, password: String): Flow<State<AuthResult>>
}