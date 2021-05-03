package com.proct.activities.inreal.data.model

import androidx.room.*

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

class DishConverter {

    @TypeConverter
    fun fromDish(dish: Dish): String {
        val typeConverter = DishTypeConverter()
        val type = typeConverter.fromDishType(dish.type)
        return "${dish.name}&&&${dish.description}&&&${dish.price}&&&${dish.imageId}&&&${dish.calories}&&&${dish.ingredients}&&&${type}&&&${dish.weight}&&&${dish.rawForObject}"
    }

    @TypeConverter
    fun toDish(data: String) : Dish {
        val array = data.split("&&&")
        val typeConverter = DishTypeConverter()
        val type = typeConverter.toDishType(array[6].toInt())
        return Dish(
            array[0],
            array[1],
            array[2],
            array[3].toInt(),
            array[4],
            array[5],
            type,
            array[7],
            array[8].toInt()
        )
    }

}