package com.proct.activities.inreal.data.model

import androidx.room.*
import com.proct.activities.inreal.R

@Entity(tableName = "orders")
data class OrderItem(
    @PrimaryKey
    @ColumnInfo(name = "dish")
    @TypeConverters(DishConverter::class)
    var dish: Dish,

    @ColumnInfo(name = "countOfDish")
    var countOfDish: Int,

    @ColumnInfo(name = "currentPrice")
    var currentPrice: Int
)


