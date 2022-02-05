package ru.zdanovich.developerslife.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.zdanovich.developerslife.domain.models.Similarable

class DiffUtilCallbackFactory constructor(
    private val diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory
) {

    fun <T : Similarable<T>> create(oldList: List<T>, newList: List<T>): DiffUtil.Callback {

        val diffUtilItemCallback = diffUtilItemCallbackFactory.create<T>()

        return object : DiffUtil.Callback() {
            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                diffUtilItemCallback.areItemsTheSame(
                    oldList[oldItemPosition],
                    newList[newItemPosition]
                )

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                diffUtilItemCallback.areContentsTheSame(
                    oldList[oldItemPosition],
                    newList[newItemPosition]
                )
        }
    }
}
