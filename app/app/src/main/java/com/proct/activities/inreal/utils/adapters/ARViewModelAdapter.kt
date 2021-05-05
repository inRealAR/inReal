package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ARViewModelAdapter @Inject constructor(
    var inRealDataSource: InRealDataLocalSource
){
    var name: String = "none"

    suspend fun getDishForAR() : Dish = inRealDataSource.getDish(name)
}
