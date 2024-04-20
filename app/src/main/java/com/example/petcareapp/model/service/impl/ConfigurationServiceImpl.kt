package com.example.petcareapp.model.service.impl

import com.example.petcareapp.model.service.ConfigurationService
import javax.inject.Inject

class ConfigurationServiceImpl @Inject constructor(): ConfigurationService {
    override suspend fun fetchConfiguration(): Boolean {
        TODO("Not yet implemented")
    }

    override val isShowTaskEditButtonConfig: Boolean
        get() = TODO("Not yet implemented")
}