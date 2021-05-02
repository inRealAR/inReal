package com.proct.activities.inreal.views.main.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.adapters.DishCardAdapter
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.di.ViewModelFactory
import com.proct.activities.inreal.utils.diffutils.dishes.DiffUtilsDishes
import com.proct.activities.inreal.viewmodels.dishes.DishesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentDishes : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var listener: DishesListener
    private lateinit var viewModel: DishesViewModel
    private var adapter: DishCardAdapter? = null
    private var listOfDishes: List<Dish> = emptyList()
    private lateinit var mainNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainNavController = findNavController()

        val recyclerView =
            inflater.inflate(
                R.layout.fragment_main__dishes__dishes,
                container,
                false
            ) as RecyclerView

        viewModel = viewModels<DishesViewModel> { viewModelFactory }.value

        listener = object : DishesListener {
            override fun onClickDish(name: String) {
                mainNavController.navigate(R.id.fragmentDetailedDish)
                viewModel.setName(name)
            }

        }

        adapter = DishCardAdapter(listOfDishes)
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
        viewModel.dishesList.observe(viewLifecycleOwner) {
            update(it)
        }
    }

    private fun update(list: List<Dish>) {
        if (adapter == null) {
            adapter = DishCardAdapter(listOfDishes)
            adapter!!.initListener(listener)
        }
        val diff = DiffUtil.calculateDiff(DiffUtilsDishes(adapter!!.dishes, list))
        adapter!!.dishes = list
        diff.dispatchUpdatesTo(adapter!!)
    }

    interface DishesListener {
        fun onClickDish(name: String)
    }


}
