package com.proct.activities.inreal.views.main.order.fill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proct.activities.inreal.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillOrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main__order__fill_order, container, false)
    }

}
