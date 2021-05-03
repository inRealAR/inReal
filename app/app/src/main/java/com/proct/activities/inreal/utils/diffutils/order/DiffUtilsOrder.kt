package com.proct.activities.inreal.utils.diffutils.order

import androidx.recyclerview.widget.DiffUtil
import com.proct.activities.inreal.data.model.OrderItem

class DiffUtilsOrder(private val oldList: List<OrderItem>, private val newList: List<OrderItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition] && oldItemPosition == newItemPosition
}