package com.proct.activities.inreal.viewmodels.dishes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.utils.adapters.DetailedDishViewModelAdapter
import com.proct.activities.inreal.utils.providers.DetailedDishAndOrderProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedDishViewModel @Inject constructor(
    var provider: DetailedDishAndOrderProvider,
    var adapter: DetailedDishViewModelAdapter
) : ViewModel() {

    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish>
        get() = _dish

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dish = adapter.getDish()
            Log.e("DetailedDishViewModel", "$dish.name")
            _dish.postValue(dish)
        }
    }

    fun getDish() {
        viewModelScope.launch(Dispatchers.IO) {
            val dish = adapter.getDish()
            _dish.postValue(dish)
        }
    }

    fun setDishToOrder(dish: Dish) {
        viewModelScope.launch( Dispatchers.IO) {
            provider.setDishToOrder(dish)
        }
    }
}
