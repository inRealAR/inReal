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
            val listOfCategories: MutableList<Category> =
                adapter.getCategoriesList().first().toMutableList()
            if (listOfCategories.isEmpty()) {
                Log.e("h", "h")
            }
            _categoriesList.postValue(listOfCategories)
        }

        viewModelScope.launch(Dispatchers.IO) {
            adapter.getCategoriesList().drop(1).collect {
                _categoriesList.postValue(it.toMutableList())
            }
        }

//        viewModelScope.launch(Dispatchers.IO) {
//            val listOfCategories : MutableList<Category> = adapter.getCategoriesList().first().toMutableList()
//            if(listOfCategories.isEmpty()) {
//                Log.e("h", "h")
//            }
//            _categoriesList.postValue(listOfCategories)
//        }
    }

    fun setType(type: DishType) {
        provider.setType(type)
    }

}