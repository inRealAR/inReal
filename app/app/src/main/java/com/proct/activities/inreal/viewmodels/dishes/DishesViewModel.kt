package com.proct.activities.inreal.viewmodels.dishes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import com.proct.activities.inreal.utils.providers.DishesAndDetailedDishViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DishesViewModel(
    var provider: DishesAndDetailedDishViewModelProvider,
    var adapter: DishesViewModelAdapter
) : ViewModel() {

    private val _dishesList = MutableLiveData<MutableList<Dish>>()
    val dishesList: LiveData<MutableList<Dish>>
        get() = _dishesList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = adapter.getDishesList().first().toMutableList()
            withContext(Dispatchers.Main) {
                _dishesList.postValue(list)
            }
        }
    }

    fun setName(name: String) {
        provider.setDishName(name)
    }
}
