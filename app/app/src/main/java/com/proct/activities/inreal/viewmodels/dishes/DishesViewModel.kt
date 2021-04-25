package com.proct.activities.inreal.viewmodels.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishesViewModel(
    var provider: CategoryAndDishesViewModelProvider,
    var adapter: DishesViewModelAdapter
) : ViewModel() {

    private val _dishesList = MutableLiveData<MutableList<Category>>()
    val dishesList: LiveData<MutableList<Category>>
        get() = _dishesList


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfDishes = adapter.getDishesList()
        }
    }
}
