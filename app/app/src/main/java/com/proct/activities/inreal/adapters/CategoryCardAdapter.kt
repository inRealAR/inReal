package com.proct.activities.inreal.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.Category
import com.proct.activities.inreal.views.main.category.FragmentCategories

class CategoryCardAdapter(
    var categories: List<Category>
) : RecyclerView.Adapter<CategoryCardAdapter.CategoriesViewHolder>() {
    private var listener: FragmentCategories.CategoriesListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryCardAdapter.CategoriesViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_for_categories, parent, false) as CardView
        return CategoriesViewHolder(cv)
    }

    fun initListener(listener: FragmentCategories.CategoriesListener) {
        this.listener = listener
    }

    inner class CategoriesViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Log.e("CategoriesViewHolder", "position: $position")
        val cardView = holder.cardView
        cardView.setOnClickListener {
            listener!!.onClickCategory(
                categories[position].type
            )
        }
        val layout = cardView.findViewById<View>(R.id.card_category_layout) as ConstraintLayout
        val imageView = layout.findViewById<View>(R.id.card_category_picture) as ImageView
        val textView = layout.findViewById<View>(R.id.card_category_name_text_view) as TextView

        imageView.setImageResource(categories[position].imageId)
        textView.text = categories[position].name
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
