package com.proct.activities.inreal.utils.diffutils.dishes

import androidx.recyclerview.widget.DiffUtil
import com.proct.activities.inreal.data.model.Dish

class DiffUtilsDishes (private val oldList: List<Dish>, private val newList: List<Dish>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition] && oldItemPosition == newItemPosition
}