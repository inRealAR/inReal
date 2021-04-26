package com.proct.activities.inreal.data.sources

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.model.OrderItem
import javax.inject.Singleton

@Singleton
class InRealDataLocalSource : InRealDataSource {
    override suspend fun getCategories(): List<Category> {
       return emptyList()
    }

    override suspend fun getDishesList(type: DishType): List<Dish> {
        return emptyList()
    }

    override suspend fun getOrderItemsList(): List<OrderItem> {
        return emptyList()
    }


}