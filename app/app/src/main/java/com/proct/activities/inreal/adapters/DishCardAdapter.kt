package com.proct.activities.inreal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.Dish
import com.proct.activities.inreal.views.main.dishes.FragmentDishes

class DishCardAdapter(
    var dishes: List<Dish>
) : RecyclerView.Adapter<DishCardAdapter.DishViewHolder>() {
    private var listener : FragmentDishes.DishesListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DishCardAdapter.DishViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_for_dishes, parent, false) as CardView
        return DishViewHolder(cv)
    }

    fun initListener(listener : FragmentDishes.DishesListener) {
        this.listener = listener
    }

    inner class DishViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val cardView = holder.cardView
        val currentDish: Dish = dishes[position]

        val layout = cardView.findViewById<LinearLayout>(R.id.card_for_dish_layout)
        val image = layout.findViewById<ImageView>(R.id.card_for_dish_picture)
        val innerCardView: CardView = layout.findViewById(R.id.card_for_dish_card_for_description)
        val title = innerCardView.findViewById<TextView>(R.id.card_for_dish_name_text_view)
        val description =
            innerCardView.findViewById<TextView>(R.id.card_for_dish_description_text_view)
        val price = innerCardView.findViewById<TextView>(R.id.card_for_dish_price_text_view)


        val button = cardView.findViewById<View>(R.id.card_for_dish_button_for_choice)

        button.setOnClickListener{
            listener!!.onClickDish(
                dishes[position].name
            )
        }

        image.setOnClickListener {
            listener!!.onClickDish(
                dishes[position].name
            )
        }

        image.setImageResource(currentDish.imageId)
        title.text = currentDish.name
        description.text = currentDish.description
        price.text = currentDish.price

    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}