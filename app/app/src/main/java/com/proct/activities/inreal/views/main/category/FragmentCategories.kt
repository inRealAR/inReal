package com.proct.activities.inreal.views.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.adapters.CategoryCardAdapter
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.data.model.DishType
import com.proct.activities.inreal.di.ViewModelFactory
import com.proct.activities.inreal.utils.diffutils.category.DiffUtilsCategory
import com.proct.activities.inreal.viewmodels.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCategories : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var listener: CategoriesListener
    private lateinit var viewModel: CategoryViewModel
    private var adapter: CategoryCardAdapter? = null
    private var listOfCategories: MutableList<Category>? = null
    private lateinit var mainNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainNavController = findNavController()

        val recyclerView =
            inflater.inflate(R.layout.fragment_main__categories, container, false) as RecyclerView

        viewModel = viewModels<CategoryViewModel> { viewModelFactory }.value

        listener = object : CategoriesListener {
            override fun onClickCategory(type: DishType) {
                viewModel.setType(type)
                mainNavController.navigate(R.id.fragmentDishes)
            }

        }

        adapter = CategoryCardAdapter(mutableListOf())
        adapter!!.initListener(listener)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()

    }

    private fun initObservers() {
        viewModel.categoriesList.observe(viewLifecycleOwner) {
            update(it)
        }
    }

    private fun update(list: List<Category>) {
        if (adapter == null) {
            adapter = CategoryCardAdapter(listOfCategories!!)
            adapter!!.initListener(listener)
        }
        val diff = DiffUtil.calculateDiff(DiffUtilsCategory(adapter!!.categories, list))
        adapter!!.categories = list
        diff.dispatchUpdatesTo(adapter!!)
    }

    interface CategoriesListener {
        fun onClickCategory(type: DishType)
    }
}
