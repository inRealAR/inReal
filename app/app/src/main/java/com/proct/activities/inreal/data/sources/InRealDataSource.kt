package com.proct.activities.inreal.data.sources

import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface InRealDataSource {
    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getDishesList(type: DishType): Flow<List<Dish>>
    suspend fun getOrderItemsList(): Flow<List<OrderItem>>
    suspend fun setOrderItemsList(list: List<OrderItem>)
    suspend fun getDish(name: String) : Dish
}