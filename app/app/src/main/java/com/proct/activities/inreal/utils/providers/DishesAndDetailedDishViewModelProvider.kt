package com.proct.activities.inreal.utils.providers

import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import javax.inject.Inject

class DishesAndDetailedDishViewModelProvider @Inject constructor(
    var detailedDishViewModelAdapter: DetailedDishViewModelAdapter
) {
    fun setDishName(name: String) {
        detailedDishViewModelAdapter.name = name
    }
}
