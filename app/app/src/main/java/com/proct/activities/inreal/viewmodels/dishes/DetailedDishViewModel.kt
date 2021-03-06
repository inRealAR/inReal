package com.proct.activities.inreal.viewmodels.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import com.proct.activities.inreal.utils.providers.DetailedDishAndOrderViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedDishViewModel @Inject constructor(
    var providerOrder: DetailedDishAndOrderViewModelProvider,
    var adapter: DetailedDishViewModelAdapter
) : ViewModel() {

    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish>
        get() = _dish

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dish = adapter.getDish()
            if (dish.name != "none") {

                withContext(Dispatchers.Main) {
                    _dish.postValue(dish)
                }
            }
        }
    }

    fun setDishToOrder(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            providerOrder.setDishToOrder(dish)
        }
    }
}
