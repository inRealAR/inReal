package com.proct.activities.inreal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.proct.activities.inreal.data.model.*

@Database(entities = [Dish::class, Category::class, OrderItem::class], version = 1)
@TypeConverters(DishTypeConverter::class, DishConverter::class)
abstract class InRealDatabase : RoomDatabase() {
    abstract fun dishDao() : DishDAO
    abstract fun categoryDao() : CategoryDAO
    abstract fun orderDao() : OrderItemDAO
}

fun <T : RoomDatabase> RoomDatabase.Builder<T>.addCreateCallback(insert: () -> Unit) =
    this.addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            insert.invoke()
        }
    })
