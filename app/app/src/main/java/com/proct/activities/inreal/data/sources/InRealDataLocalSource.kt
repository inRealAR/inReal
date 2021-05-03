package com.proct.activities.inreal.data.sources

import androidx.room.FtsOptions
import com.proct.activities.inreal.data.database.CategoryDAO
import com.proct.activities.inreal.data.database.DishDAO
import com.proct.activities.inreal.data.database.OrderItemDAO
import com.proct.activities.inreal.data.model.*
import com.proct.activities.inreal.di.DataStoreScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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

    override suspend fun setOrderItemsList(list: List<OrderItem>) {
        DataStoreScope.launch {
            for(orderItem in list) {
                orderItemDAO.insert(orderItem)
            }
        }
    }

    override suspend fun getDish(name: String): Dish = dishDAO.getDish(name)
}