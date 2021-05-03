package com.proct.activities.inreal.views.main.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.di.ViewModelFactory
import com.proct.activities.inreal.viewmodels.dishes.DetailedDishViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String
import javax.inject.Inject

@AndroidEntryPoint
class FragmentDetailedDish : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var listener: DetailedDishListener
    private lateinit var viewModel: DetailedDishViewModel
    private lateinit var mainNavController: NavController

    private lateinit var dish: Dish

    private lateinit var nameOfDish: TextView
    private lateinit var kbguOfDish: TextView
    private lateinit var pictureOfDish: ImageView
    private lateinit var ingredientsOfDish: TextView
    private lateinit var priceOfDish: TextView
    private lateinit var seeInAR: Button
    private lateinit var addToOrder: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainNavController = findNavController()

        viewModel = viewModels<DetailedDishViewModel> { viewModelFactory }.value

        listener = object : DetailedDishListener {

            override fun onClickAddToOrder(dish: Dish) {
                mainNavController.navigate(R.id.mainOrderFragment)
                viewModel.setDishToOrder(dish)
            }

            override fun onClickSeeInAR() {
                mainNavController.navigate(R.id.ARFragment)
            }
        }

        return inflater.inflate(R.layout.fragment_main__dishes__detailed_dish, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
        nameOfDish =
            requireView().findViewById(R.id.detailed_card_for_dish_name_text_view)
        kbguOfDish =
            requireView().findViewById(R.id.detailed_card_for_dish_kbgu_text_view)
        pictureOfDish =
            requireView().findViewById(R.id.detailed_card_for_dish_picture)
        ingredientsOfDish =
            requireView().findViewById(R.id.detailed_card_for_dish_ingridients_text_view)
        priceOfDish =
            requireView().findViewById(R.id.detailed_card_for_dish_price_text_view)

        seeInAR = requireView().findViewById(R.id.detailed_card_for_dish_button_see_in_ar)
        seeInAR.setOnClickListener { listener.onClickSeeInAR() }

        addToOrder =
            requireView().findViewById(R.id.detailed_card_for_dish_button_for_choice)
        addToOrder.setOnClickListener {
            listener.onClickAddToOrder(dish)
        }
    }

    private fun initObservers() {
        viewModel.dish.observe(viewLifecycleOwner) {
            update(it)
        }
    }

    private fun update(dish: Dish) {
        this.dish = dish

        nameOfDish.text = dish.name
        kbguOfDish.text = dish.calories
        pictureOfDish.setImageResource(dish.imageId)
        ingredientsOfDish.text = dish.ingredients
        priceOfDish.text = String.format(
            "%s/%s",
            dish.weight,
            dish.price
        )
    }
}

interface DetailedDishListener {
    fun onClickAddToOrder(dish: Dish)
    fun onClickSeeInAR()
}
