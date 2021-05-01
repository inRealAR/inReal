package com.proct.activities.inreal.data.sources

import com.proct.activities.inreal.data.database.CategoryDAO
import com.proct.activities.inreal.data.database.DishDAO
import com.proct.activities.inreal.data.database.OrderItemDAO
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.data.model.OrderItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InRealDataLocalSource @Inject constructor(
    private val dishDAO: DishDAO,
    private val categoryDAO: CategoryDAO,
    private val orderItemDAO: OrderItemDAO
): InRealDataSource {
    override suspend fun getCategories(): List<Category> = categoryDAO.getListCategories()

    override suspend fun getDishesList(type: DishType): List<Dish> = dishDAO.getDishesList(type)

    override suspend fun getOrderItemsList(): List<OrderItem> = orderItemDAO.getListOrder()
}