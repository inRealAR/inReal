package com.proct.activities.inreal.views.main.order.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.proct.activities.inreal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyOrderFragment : Fragment() {

    private lateinit var mainNavController: NavController

    private lateinit var buttonBackToCategories: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainNavController = findNavController()

        return inflater.inflate(R.layout.fragment_main__order__empty_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonBackToCategories =
            requireView().findViewById(R.id.fragment_empty_order_button_categories)
        buttonBackToCategories.setOnClickListener {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.emptyOrderFragment, true).build()
            mainNavController.navigate(R.id.fragmentCategories, null, navOptions)
        }
    }
}
