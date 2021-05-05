package com.proct.activities.inreal

import android.app.Application
import android.util.Log
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import com.proct.activities.inreal.di.DataStoreScope
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class InRealApp : Application() {

    @Inject
    lateinit var inRealDataLocalSource: InRealDataLocalSource

    override fun onCreate() {
        super.onCreate()

        for (category in Category.ListOfCategoriesListener.listOfCategories) {
            Log.e("DatabaseModule", "insert ${category.name}")
            DataStoreScope.launch(Dispatchers.IO) {
                inRealDataLocalSource.insertCategory(category)
            }
        }
        for (dish in Dish.ListOfDishesLoader.listOfDishes) {
            DataStoreScope.launch(Dispatchers.IO) {
                inRealDataLocalSource.insertDish(dish)
            }
        }
    }
}
