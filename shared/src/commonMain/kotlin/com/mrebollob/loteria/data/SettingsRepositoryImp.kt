package com.mrebollob.loteria.data

import com.mrebollob.loteria.data.preferences.Preferences
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsRepositoryImp : KoinComponent, SettingsRepository {

    private val preferences: Preferences by inject()

    override suspend fun getSortingMethod(): SortingMethod {
        val sortingMethodValue = preferences.getString(SORTING_METHOD_KEY, SortingMethod.NAME.name)
        return SortingMethod.valueOf(sortingMethodValue)
    }

    override suspend fun saveSortingMethod(sortingMethod: SortingMethod) {
        preferences.setString(SORTING_METHOD_KEY, sortingMethod.name)
    }

    companion object {
        private const val SORTING_METHOD_KEY = "sorting_method"
    }
}
