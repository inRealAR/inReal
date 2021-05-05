package com.proct.activities.inreal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.proct.activities.inreal.R

@Entity(tableName = "categories")
data class Category(

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "imageId")
    var imageId: Int,

    @ColumnInfo(name = "type")
    var type: DishType
) {
    object ListOfCategoriesListener {
        val listOfCategories: MutableList<Category> = mutableListOf(
            Category("Салаты и холодные закуски", R.drawable.salad, DishType.SALADS),
            Category("Супы", R.drawable.soup, DishType.SOUPS),
            Category("Горячие блюда", R.drawable.main_dish, DishType.HOT),
            Category("Десерты", R.drawable.dessert, DishType.DESSERTS),
            Category("Напитки", R.drawable.drinks, DishType.DRINKS)
        )
    }
}
