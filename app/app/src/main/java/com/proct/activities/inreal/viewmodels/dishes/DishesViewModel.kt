package com.proct.activities.inreal.viewmodels.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.utils.adapters.DishesViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishesViewModel(
    var provider: CategoryAndDishesViewModelProvider,
    var adapter: DishesViewModelAdapter
) : ViewModel() {

    private val _dishesList = MutableLiveData<MutableList<Dish>>()
    val dishesList: LiveData<MutableList<Dish>>
        get() = _dishesList


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfDishes = adapter.getDishesList()
            _dishesList.postValue(listOfDishes.toMutableList())
        }
    }

    fun getDishesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfDishes = adapter.getDishesList()
            _dishesList.postValue(listOfDishes.toMutableList())
        }
    }


}
