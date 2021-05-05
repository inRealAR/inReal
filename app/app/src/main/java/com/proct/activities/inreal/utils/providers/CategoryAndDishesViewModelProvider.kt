package com.proct.activities.inreal.utils.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryAndDishesViewModelProvider @Inject constructor(
    var dishesViewModelAdapter: DishesViewModelAdapter
) {
    private val _category = MutableLiveData<DishType>()
    val category: LiveData<DishType>
        get() = _category


    init {
        CoroutineScope(Dispatchers.IO).launch {
            _category.postValue(DishType.NONE)
        }
    }

    fun setType(type: DishType) {
        _category.postValue(type)
        dishesViewModelAdapter.type = type
    }
}
