package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import com.proct.activities.inreal.di.DataStoreScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryViewModelAdapter @Inject constructor(
    var inRealDataSource: InRealDataLocalSource
){
    suspend fun getCategoriesList() : Flow<List<Category>> {
        return inRealDataSource.getCategories()
    }
}
