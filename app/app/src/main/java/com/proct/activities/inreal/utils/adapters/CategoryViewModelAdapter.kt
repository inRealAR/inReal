package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.repositories.InRealDataLocalSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryViewModelAdapter @Inject constructor(
    var inRealDataSource: InRealDataLocalSource
) {
    suspend fun getCategoriesList(): Flow<List<Category>> {
        return inRealDataSource.getCategories()
    }
}
