package com.mrebollob.loteria.domain.usecase.settings

import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository

class GetSortingMethod(
    private val settingsRepository: SettingsRepository
) {

    suspend fun execute(): SortingMethod = settingsRepository.getSortingMethod()
}
