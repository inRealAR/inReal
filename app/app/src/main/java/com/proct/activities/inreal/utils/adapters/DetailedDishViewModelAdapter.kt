package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailedDishViewModelAdapter @Inject constructor(
    var inRealDataSource: InRealDataLocalSource
){

    var name: String = "none"

    suspend fun getDish() : Dish = inRealDataSource.getDish(name)
}