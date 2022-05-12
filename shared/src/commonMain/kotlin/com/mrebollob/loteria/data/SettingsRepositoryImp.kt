package com.mrebollob.loteria.data

import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository

class SettingsRepositoryImp(

) : SettingsRepository {

    override suspend fun getSortingMethod(): Result<SortingMethod> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSortingMethod(sortingMethod: SortingMethod): Result<Unit> {
        TODO("Not yet implemented")
    }
}
