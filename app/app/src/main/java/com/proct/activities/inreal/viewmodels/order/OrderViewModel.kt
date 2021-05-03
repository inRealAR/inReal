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
import kotlinx.coroutines.launch

class OrderViewModel(
    var adapter: OrderViewModelAdapter
) : ViewModel() {

    private val _orderItemsList = MutableLiveData<MutableList<OrderItem>>()
    val orderItemsList: LiveData<MutableList<OrderItem>>
        get() = _orderItemsList

    init {

        viewModelScope.launch(Dispatchers.IO) {
            adapter.getOrderList().drop(1).collect {
                _orderItemsList.postValue(it.toMutableList())
            }
        }
    }

    fun increment(item: OrderItem) {
        viewModelScope.launch(Dispatchers.Default) {
            adapter.increment(item)
        }

        viewModelScope.launch(Dispatchers.IO) {
            adapter.getOrderList().drop(1).collect {
                _orderItemsList.postValue(it.toMutableList())
            }
        }
    }

    fun delete(item: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            adapter.delete(item)
            adapter.getOrderList().drop(1).collect {
                _orderItemsList.postValue(it.toMutableList())
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
