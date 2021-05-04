package com.proct.activities.inreal.viewmodels.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderViewModel(
    var adapter: OrderViewModelAdapter
) : ViewModel() {

    private val _orderItemsList = MutableLiveData<List<OrderItem>>()
    val orderItemsList: LiveData<List<OrderItem>>
        get() = _orderItemsList

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val list = adapter.getOrderList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _orderItemsList.postValue(list)
            }
        }
    }

    fun increment(item: OrderItem) {
        viewModelScope.launch(Dispatchers.Default) {
            adapter.increment(item)
            val list = adapter.getOrderList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _orderItemsList.postValue(list)
            }
        }

    }

    fun delete(item: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            adapter.delete(item)
            val list = adapter.getOrderList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _orderItemsList.postValue(list)
            }
        }
    }

    fun decrement(item: OrderItem) {
        Log.e("OrderViewModel", item.dish.name)
        viewModelScope.launch(Dispatchers.IO) {
            if (item.countOfDish == 1) {
                delete(item)
            } else {
                for (orderItem in _orderItemsList.value!!) {
                    if (orderItem == item) {
                        if (orderItem.countOfDish == 1) {
                            delete(orderItem)
                            break
                        } else {
                            val defaultPrice: Int = item.dish.price.toInt()
                            val currentCount: Int = item.countOfDish - 1
                            item.countOfDish = currentCount
                            item.currentPrice = defaultPrice * currentCount
                            adapter.setOrderList(_orderItemsList.value!!.toList())
                            break
                        }
                    }
                }
            }
        }
    }
}
