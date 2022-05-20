package com.mrebollob.loteria.domain.repository

import com.mrebollob.loteria.domain.entity.SortingMethod

interface SettingsRepository {

    suspend fun getSortingMethod(): SortingMethod

    suspend fun saveSortingMethod(sortingMethod: SortingMethod)
}
