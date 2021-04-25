package com.proct.activities.inreal.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.utils.adapters.OrderViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(
    var adapter: OrderViewModelAdapter
) : ViewModel() {

    private val _orderItemsList = MutableLiveData<MutableList<Category>>()
    val orderItemsList: LiveData<MutableList<Category>>
        get() = _orderItemsList


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfOrderItems = adapter.getOrderList()
        }
    }
}