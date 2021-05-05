package com.proct.activities.inreal.repositories

import com.proct.activities.inreal.data.database.CategoryDAO
import com.proct.activities.inreal.data.database.DishDAO
import com.proct.activities.inreal.data.database.OrderItemDAO
import com.proct.activities.inreal.data.model.*
import com.proct.activities.inreal.data.sources.InRealDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InRealDataLocalSource @Inject constructor(
    private val dishDAO: DishDAO,
    private val categoryDAO: CategoryDAO,
    private val orderItemDAO: OrderItemDAO
) : InRealDataSource {
    override suspend fun getCategories(): Flow<List<Category>> = categoryDAO.getListCategories()

    override suspend fun getDishesList(type: DishType): Flow<List<Dish>> {
        val typeConverter = DishTypeConverter()
        val dishType = typeConverter.fromDishType(type)
        return dishDAO.getDishesList(dishType)
    }

    override suspend fun getOrderItemsList(): Flow<List<OrderItem>> = orderItemDAO.getListOrder()

    override suspend fun insertOrderItem(orderItem: OrderItem) = orderItemDAO.insert(orderItem)

    override suspend fun insertCategory(category: Category) = categoryDAO.insert(category)

    override suspend fun insertDish(dish: Dish) = dishDAO.insert(dish)

    override suspend fun getDish(name: String): Dish = dishDAO.getDish(name)

    override suspend fun deleteOrderItem(orderItem: OrderItem) = orderItemDAO.delete(orderItem)
}