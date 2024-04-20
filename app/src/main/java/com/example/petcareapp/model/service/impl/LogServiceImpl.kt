package com.example.petcareapp.model.service.impl

import com.example.petcareapp.model.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}