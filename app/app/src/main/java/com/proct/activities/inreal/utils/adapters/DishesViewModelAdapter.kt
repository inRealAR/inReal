package com.proct.activities.inreal.utils.adapters

import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType

class DishesViewModelAdapter {
    var type : DishType? = null

    suspend fun getDishesList() : List<Dish> {
        return emptyList()
    }

}