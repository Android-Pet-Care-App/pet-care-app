package com.example.petcareapp.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}