package com.proct.activities.inreal.viewmodels.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.utils.adapters.CategoryViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    var provider: CategoryAndDishesViewModelProvider,
    var adapter: CategoryViewModelAdapter
) : ViewModel() {

    private val _categoriesList = MutableLiveData<MutableList<Category>>()
    val categoriesList: LiveData<MutableList<Category>>
        get() = _categoriesList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = adapter.getCategoriesList().first().toMutableList()
            Log.e("OrderViewModel", "LIST RETURN $list")
            withContext(Dispatchers.Main) {
                _categoriesList.postValue(list)
            }
        }
    }

    fun setType(type: DishType) {
        provider.setType(type)
    }

}