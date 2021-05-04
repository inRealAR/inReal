package com.proct.activities.inreal.data.model

import androidx.room.TypeConverter

class DishTypeConverter {
    @TypeConverter
    fun fromDishType(type: DishType): Int{
        return type.ordinal
    }

    @TypeConverter
    fun toDishType(value: Int): DishType{
        return when(value){
            1 -> DishType.SALADS
            2 -> DishType.SOUPS
            3 -> DishType.HOT
            4 -> DishType.DESSERTS
            5 -> DishType.DRINKS
            else -> DishType.NONE
        }
    }
}