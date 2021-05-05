package com.proct.activities.inreal.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.proct.activities.inreal.R
import com.proct.activities.inreal.data.model.OrderItem
import com.proct.activities.inreal.views.main.order.MainOrderFragment

class OrderCardAdapter(
    private var clickDish: MainOrderFragment.ListenerClickDish
) :
    RecyclerView.Adapter<OrderCardAdapter.ViewHolderForOrder>() {

    var orderItems: List<OrderItem> = emptyList()
        set(value) {
            field = value
            Log.e("OrderCardAdapter", "Setting $value")
            notifyDataSetChanged()
        }

    inner class ViewHolderForOrder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForOrder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_for_order, parent, false) as CardView
        return ViewHolderForOrder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolderForOrder, position: Int) {
        val cardView = holder.cardView
        val nameOfDish = cardView.findViewById<TextView>(R.id.order_card_name)
        val imageOfDish = cardView.findViewById<ImageView>(R.id.order_card_picture)
        val priceOfDish = cardView.findViewById<TextView>(R.id.order_card_total_cost)
        val count = cardView.findViewById<TextView>(R.id.order_card_count)
        val currentItem: OrderItem = orderItems[position]
        count.text = currentItem.countOfDish.toString()
        priceOfDish.text = currentItem.currentPrice.toString()
        nameOfDish.text = currentItem.dish.name
        imageOfDish.setImageResource(currentItem.dish.imageId)
        val plus = cardView.findViewById<ImageView>(R.id.order_card_plus)
        val minus = cardView.findViewById<ImageView>(R.id.order_card_minus)
        plus.setOnClickListener {
            clickDish.onPlusClickListener(currentItem)
            count.text = currentItem.countOfDish.toString()
            priceOfDish.text = currentItem.currentPrice.toString()
        }
        minus.setOnClickListener {
            clickDish.onMinusClickListener(currentItem)
            count.text = currentItem.countOfDish.toString()
            priceOfDish.text = currentItem.currentPrice.toString()
        }
        val delete = cardView.findViewById<ImageView>(R.id.order_card_delete)
        delete.setOnClickListener {
            clickDish.onDeleteClickListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }
}
