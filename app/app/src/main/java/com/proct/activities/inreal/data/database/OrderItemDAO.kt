package com.proct.activities.inreal.data.database

import androidx.room.*
import com.proct.activities.inreal.data.model.OrderItem
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderItemDAO {

    @Query("select * from orders order by dish")
    fun getListOrder() : Flow<List<OrderItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderItem: OrderItem)

}
