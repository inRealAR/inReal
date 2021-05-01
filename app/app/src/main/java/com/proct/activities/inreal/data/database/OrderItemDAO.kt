package com.proct.activities.inreal.data.database

import androidx.room.Dao
import androidx.room.Query
import com.proct.activities.inreal.data.model.OrderItem


@Dao
interface OrderItemDAO {

    @Query("select * from orders order by dish")
    fun getListOrder() : List<OrderItem>

}
