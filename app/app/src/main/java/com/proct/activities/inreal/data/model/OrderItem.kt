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
) {
    object ListOfOrderListener {
        val listOfOrderItems  : MutableList<OrderItem> = mutableListOf(
           OrderItem(Dish(
               "Салат",
               "Листья салата в\nсоке лимона и специях",
               "250",
               R.drawable.standart_salad,
               "17; 2; 1; 2.7",
               "Листья салата",
               DishType.SALADS,
               "100 Г",
               R.raw.standart_salad),
           1,
           250),
            OrderItem(Dish(
                "Салат Топ",
                "Листья салата в\nсоке лимона и специях",
                "350",
                R.drawable.standart_salad,
                "17; 2; 1; 2.7",
                "Листья салата",
                DishType.SALADS,
                "100 Г",
                R.raw.standart_salad),
                1,
                350)
        )
    }
}

