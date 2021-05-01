package com.proct.activities.inreal.data.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType

@Dao
interface DishDAO {

    @Query("select * from dishes where type = :dishType order by name")
    fun getDishesList(dishType: DishType): List<Dish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dish: Dish)
}
