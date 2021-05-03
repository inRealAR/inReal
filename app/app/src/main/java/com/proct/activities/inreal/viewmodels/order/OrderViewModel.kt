package com.proct.activities.inreal.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(
    var adapter: OrderViewModelAdapter
) : ViewModel() {

    private val _orderItemsList = MutableLiveData<MutableList<OrderItem>>()
    val orderItemsList: LiveData<MutableList<OrderItem>>
        get() = _orderItemsList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfOrderItems = adapter.getOrderList()
            if (listOfOrderItems.isEmpty()) {
                _orderItemsList.postValue(mutableListOf())
            } else {

                _orderItemsList.postValue(listOfOrderItems.toMutableList())
            }
        }
    }

    fun increment(item: OrderItem) {
        viewModelScope.launch(Dispatchers.Default) {
            adapter.increment(item)
            val listOfOrderItems = adapter.getOrderList()
            _orderItemsList.postValue(listOfOrderItems.toMutableList())
        }
    }

    fun delete(item: OrderItem) {
        viewModelScope.launch(Dispatchers.Default) {
            adapter.delete(item)
            val listOfOrderItems = adapter.getOrderList()

            _orderItemsList.postValue(listOfOrderItems.toMutableList())
        }
    }

    fun decrement(item: OrderItem) {
        viewModelScope.launch(Dispatchers.Default) {
            adapter.decrement(item)
            val listOfOrderItems = adapter.getOrderList()
            _orderItemsList.postValue(listOfOrderItems.toMutableList())
        }
    }
}
