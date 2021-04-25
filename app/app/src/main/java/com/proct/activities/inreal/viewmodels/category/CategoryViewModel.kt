package com.proct.activities.inreal.viewmodels.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.utils.adapters.CategoryViewModelAdapter
import com.proct.activities.inreal.utils.providers.CategoryAndDishesViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            val listOfCategories = adapter.getCategoriesList()
        }
    }

}