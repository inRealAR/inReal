package com.proct.activities.inreal.views.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.internal.NavigationMenuItemView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.utils.findChildNavControllerByHostId

class MainFragment : Fragment() {

    private lateinit var mainNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()
        mainNavController.navigate(R.id.instructionsFragment)
    }

    private fun View.initViews() {
        mainNavController = findNavController()
    }
}
