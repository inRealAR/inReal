package com.proct.activities.inreal.views.main.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.proct.activities.inreal.R
import com.proct.activities.inreal.adapters.OrderCardAdapter
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.di.ViewModelFactory
import com.proct.activities.inreal.utils.diffutils.order.DiffUtilsOrder
import com.proct.activities.inreal.viewmodels.order.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainOrderFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainNavController: NavController

    private lateinit var viewModel: OrderViewModel

    private var listOfOrderItems: MutableList<OrderItem> = mutableListOf()

    private var adapterForOrder: OrderCardAdapter? = null

    private lateinit var clickDish: ListenerClickDish

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainNavController = findNavController()

        viewModel = viewModels<OrderViewModel> { viewModelFactory }.value

        clickDish = object : ListenerClickDish {

            override fun onPlusClickListener(orderItem: OrderItem) {
                viewModel.increment(orderItem)
            }

            override fun onMinusClickListener(orderItem: OrderItem) {
                viewModel.decrement(orderItem)
            }

            override fun onDeleteClickListener(orderItem: OrderItem) {
                viewModel.delete(orderItem)
            }
        }
            adapterForOrder = OrderCardAdapter(listOfOrderItems, clickDish)

        return inflater.inflate(R.layout.fragment_main__main_order, container, false)
    }

    interface ListenerClickDish {
        fun onPlusClickListener(orderItem: OrderItem)

        fun onMinusClickListener(orderItem: OrderItem)

        fun onDeleteClickListener(orderItem: OrderItem)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.orderItemsList.observe(viewLifecycleOwner) {
            update(it)
        }
    }

    private fun update(orderItemsList: List<OrderItem>) {
        if (adapterForOrder == null) {
            adapterForOrder = OrderCardAdapter(listOfOrderItems, clickDish)
        }
        val diff = DiffUtil.calculateDiff(
            DiffUtilsOrder(
                adapterForOrder!!.orderItems,
                orderItemsList
            )
        )
        adapterForOrder!!.orderItems = orderItemsList
        diff.dispatchUpdatesTo(adapterForOrder!!)

        if(orderItemsList.isEmpty()) {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.mainOrderFragment, true).build()
            mainNavController.navigate(R.id.emptyOrderFragment, null, navOptions)
        }

    }
}
