package com.mrebollob.loteria.data

import com.mrebollob.loteria.data.preferences.Preferences
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository

class SettingsRepositoryImp(
    private val preferences: Preferences
) : SettingsRepository {

    override suspend fun getSortingMethod(): Result<SortingMethod> {
        val sortingMethodValue = preferences.getString(SORTING_METHOD_KEY, SortingMethod.NAME.name)
        return Result.success(SortingMethod.valueOf(sortingMethodValue))
    }

    override suspend fun saveSortingMethod(sortingMethod: SortingMethod): Result<Unit> {
        preferences.setString(SORTING_METHOD_KEY, sortingMethod.name)
        return Result.success(Unit)
    }

    companion object {
        private const val SORTING_METHOD_KEY = "sorting_method"
    }
}