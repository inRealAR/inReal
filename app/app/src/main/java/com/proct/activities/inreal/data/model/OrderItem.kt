package com.proct.activities.inreal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

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
