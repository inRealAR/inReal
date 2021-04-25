package com.proct.activities.inreal.views.main.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.proct.activities.inreal.R

class InstructionsFragment : Fragment() {

    private lateinit var buttonStart: AppCompatButton
    private lateinit var mainNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main__instructions, container, false)
    }

    private fun View.initViews() {
        buttonStart = findViewById(R.id.qr_button_for_scan)
        mainNavController = findNavController()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()

        buttonStart.setOnClickListener{
            mainNavController.navigate(R.id.fragmentCategories)
        }
    }


}
