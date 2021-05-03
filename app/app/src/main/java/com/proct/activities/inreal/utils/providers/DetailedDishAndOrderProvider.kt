package com.proct.activities.inreal.utils.providers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailedDishAndOrderProvider @Inject constructor(
    var orderViewModelAdapter: OrderViewModelAdapter
) {
    private val _dish = MutableLiveData<Dish>()
    val dish: LiveData<Dish>
        get() = _dish

    fun setDishToOrder(dish: Dish) {
        _dish.postValue(dish)
        CoroutineScope(Dispatchers.Default).launch {

            orderViewModelAdapter.addToOrderList(OrderItem(_dish.value!!, 1, _dish.value!!.price.toInt()))
        }
    }
}
