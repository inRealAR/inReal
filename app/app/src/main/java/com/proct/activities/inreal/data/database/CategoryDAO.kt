package com.proct.activities.inreal.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proct.activities.inreal.data.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {

    @Query("SELECT * FROM categories ORDER BY name")
    fun getListCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)
}
