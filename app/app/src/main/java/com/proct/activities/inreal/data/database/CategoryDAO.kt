package com.proct.activities.inreal.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish

@Dao
interface CategoryDAO {

    @Query("SELECT * FROM categories ORDER BY name")
    fun getListCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)
}
