package com.proct.activities.inreal.utils.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.repositories.InRealDataLocalSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderViewModelAdapter @Inject constructor(
    var inRealDataLocalSource: InRealDataLocalSource
) {

    private val _orderItemsList = MutableLiveData<MutableList<OrderItem>>()
    val orderItemsList: LiveData<MutableList<OrderItem>>
        get() = _orderItemsList


    suspend fun addToOrderList(item: OrderItem) {
        Log.e("OrderViewModelAdapter", "Adding ${item.dish.name}")
        inRealDataLocalSource.insertOrderItem(item)
    }

    suspend fun increment(item: OrderItem) {

        val list = getOrderList().first().toMutableList()
        Log.e("OrderViewModelAdapter", "LIST RETURN $list")
        withContext(Dispatchers.Main) {

            _orderItemsList.postValue(list)
        }

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

        if (_orderItemsList.value!!.isNotEmpty()) {
            setOrderList(_orderItemsList.value!!)
        }

    }

    suspend fun setOrderList(list: List<OrderItem>) {
        for (orderItem in list) {
            inRealDataLocalSource.insertOrderItem(orderItem)
        }
    }

    suspend fun delete(item: OrderItem) {
        Log.e("OrderViewModel_DELETE", item.dish.name)
        inRealDataLocalSource.deleteOrderItem(item)
    }

    suspend fun getOrderList(): Flow<List<OrderItem>> = inRealDataLocalSource.getOrderItemsList()
}
