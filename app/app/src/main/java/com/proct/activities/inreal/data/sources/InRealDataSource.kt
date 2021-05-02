package com.proct.activities.inreal.data.sources

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.model.OrderItem

interface InRealDataSource {
    suspend fun getCategories(): List<Category>
    suspend fun getDishesList(type: DishType): List<Dish>
    suspend fun getOrderItemsList(): List<OrderItem>
    suspend fun getDish(name: String) : Dish
}