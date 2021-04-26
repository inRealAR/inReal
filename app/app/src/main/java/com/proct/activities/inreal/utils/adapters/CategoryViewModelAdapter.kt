package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryViewModelAdapter @Inject constructor(
    var inRealDataSource: InRealDataLocalSource
){
    suspend fun getCategoriesList() : List<Category> {
        return inRealDataSource.getCategories()
    }
}