package com.proct.activities.inreal.viewmodels.ar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.utils.adapters.ARViewModelAdapter
import com.proct.activities.inreal.utils.adapters.CategoryViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import com.proct.activities.inreal.utils.providers.DetailedDishAndARViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ARViewModel @Inject constructor(
    var adapter: ARViewModelAdapter
) : ViewModel() {

    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish>
        get() = _dish

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dish = adapter.getDishForAR()
            withContext(Dispatchers.Main) {
                _dish.postValue(dish)
                Log.e("ARViewModel", "Dish: $dish")
            }
        }
    }
}