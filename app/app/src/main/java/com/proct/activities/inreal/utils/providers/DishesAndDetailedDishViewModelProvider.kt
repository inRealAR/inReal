package com.proct.activities.inreal.utils.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesAndDetailedDishViewModelProvider @Inject constructor(
    var detailedDishViewModelAdapter: DetailedDishViewModelAdapter
) {
    private val _dishName = MutableLiveData<String>()
    val dishName: LiveData<String>
        get() = _dishName


    init {
        CoroutineScope(Dispatchers.IO).launch {
            _dishName.postValue("none")
        }
    }

    fun setDishName(name: String) {
            _dishName.postValue(name)
            detailedDishViewModelAdapter.name = name
    }


}