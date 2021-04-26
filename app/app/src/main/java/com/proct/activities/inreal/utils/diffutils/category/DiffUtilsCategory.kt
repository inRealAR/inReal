package com.proct.activities.inreal.utils.diffutils.category

import androidx.recyclerview.widget.DiffUtil
import com.proct.activities.inreal.data.model.Category

class DiffUtilsCategory(private val oldList: List<Category>, private val newList: List<Category>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition] && oldItemPosition == newItemPosition
}