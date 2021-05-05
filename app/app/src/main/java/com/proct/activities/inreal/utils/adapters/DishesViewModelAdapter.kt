package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.repositories.InRealDataLocalSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishesViewModelAdapter @Inject constructor(
    var inRealDataLocalSource: InRealDataLocalSource
) {
    var type: DishType = DishType.NONE

    suspend fun getDishesList(): Flow<List<Dish>> = inRealDataLocalSource.getDishesList(type)

}
