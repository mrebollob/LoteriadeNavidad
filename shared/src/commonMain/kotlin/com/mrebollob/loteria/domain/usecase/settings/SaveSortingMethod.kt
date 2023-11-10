package com.mrebollob.loteria.domain.usecase.settings

import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository

class SaveSortingMethod(
    private val settingsRepository: SettingsRepository
) {

    suspend fun execute(sortingMethod: SortingMethod) =
        settingsRepository.saveSortingMethod(sortingMethod = sortingMethod)
}
