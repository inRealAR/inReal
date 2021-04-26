package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishesViewModelAdapter @Inject constructor(
    var inRealDataLocalSource: InRealDataLocalSource
) {
    var type: DishType? = null

    suspend fun getDishesList(): List<Dish> {
        return emptyList()
    }

}