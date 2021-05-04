package com.proct.activities.inreal.viewmodels.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

class OrderViewModel(
    var adapter: OrderViewModelAdapter
) : ViewModel() {

    private val _orderItemsList = MutableLiveData<List<OrderItem>>()
    val orderItemsList: LiveData<List<OrderItem>>
        get() = _orderItemsList

    private val _allPrice = MutableLiveData<Int>()
    val allPrice: LiveData<Int>
        get() = _allPrice

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val list = adapter.getOrderList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _orderItemsList.postValue(list)
            }
            val price = AtomicInteger(0)

            list.asFlow().collect {
                price.addAndGet(it.countOfDish * it.dish.price.toInt())
            }

            withContext(Dispatchers.Main) {
                _allPrice.postValue(price.get())
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
            val price = AtomicInteger(0)
            list.asFlow().collect {
                price.addAndGet(it.countOfDish * it.dish.price.toInt())
            }

            withContext(Dispatchers.Main) {
                _allPrice.postValue(price.get())
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

            val price = AtomicInteger(0)
            list.asFlow().collect {
                price.addAndGet((it.countOfDish * it.dish.price.toInt()))
            }

            withContext(Dispatchers.Main) {
                _allPrice.postValue(price.get())
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

            val list = adapter.getOrderList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _orderItemsList.postValue(list)
            }

            val price = AtomicInteger(0)
            list.asFlow().collect {
                price.addAndGet((it.countOfDish * it.dish.price.toInt()))
            }

            withContext(Dispatchers.Main) {
                _allPrice.postValue(price.get())
            }
        }
    }
}
