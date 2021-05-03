package com.proct.activities.inreal.utils.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.data.sources.InRealDataLocalSource
import com.proct.activities.inreal.di.DataStoreScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderViewModelAdapter @Inject constructor(
    var inRealDataLocalSource: InRealDataLocalSource
) {

    private val _orderItemsList = MutableLiveData<MutableList<OrderItem>>()
    val orderItemsList: LiveData<MutableList<OrderItem>>
        get() = _orderItemsList


    private fun addToOrderList(item: OrderItem) {
        item.countOfDish = 1
        _orderItemsList.value!!.add(item)
    }

    suspend fun increment(item: OrderItem) {
        DataStoreScope.launch {
            if (_orderItemsList.value == null) {
                _orderItemsList.postValue(mutableListOf(item))
            } else if (_orderItemsList.value!!.isEmpty()) {
                    addToOrderList(item)
                } else {
                    var isFind = false
                    for (itemToFind in _orderItemsList.value!!) {
                        if (itemToFind.dish == item.dish) {
                            isFind = true
                            val defaultPrice: Int = itemToFind.dish.price.toInt()
                            val currentCount: Int = itemToFind.countOfDish + 1
                            itemToFind.countOfDish = currentCount
                            itemToFind.currentPrice = defaultPrice * currentCount
                            break
                        }
                    }
                    if (!isFind) {
                        addToOrderList(item)
                    }
                }
            setOrderList()
        }
    }

    private suspend fun setOrderList() {
        DataStoreScope.launch {
            inRealDataLocalSource.setOrderItemsList(_orderItemsList.value!!.toList())
        }
    }

    suspend fun delete(item: OrderItem) {
        DataStoreScope.launch {
            Log.e("OrderViewModel_DELETE", item.dish.name)
            _orderItemsList.value!!.remove(item)
            setOrderList()
        }
    }

    suspend fun decrement(item: OrderItem) {
        DataStoreScope.launch {
            Log.e("OrderViewModel", item.dish.name)
            if (item.countOfDish == 1) {
                delete(item)
            } else {
                val defaultPrice: Int = item.dish.price.toInt()
                val currentCount: Int = item.countOfDish - 1
                item.countOfDish = currentCount
                item.currentPrice = defaultPrice * currentCount
            }
            setOrderList()
        }
    }

    suspend fun getOrderList(): List<OrderItem> {
        if (_orderItemsList.value == null) {
            val listOfOrderItems = inRealDataLocalSource.getOrderItemsList().first()
            if (listOfOrderItems.isEmpty()) {
                return mutableListOf()
            } else {
                _orderItemsList.postValue(listOfOrderItems.toMutableList())
            }
        }
        return _orderItemsList.value!!
    }
}

