package com.proct.activities.inreal.utils.providers

import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.utils.adapters.ARViewModelAdapter
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailedDishAndARViewModelProvider @Inject constructor(
    var arViewModelAdapter: ARViewModelAdapter
) {
    suspend fun setDishToAR(name: String) {
        arViewModelAdapter.name = name
    }
}
