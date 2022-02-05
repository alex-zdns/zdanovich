package ru.zdanovich.developerslife.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import ru.zdanovich.developerslife.domain.models.Similarable


class DiffUtilItemCallbackFactory {

    fun <T : Similarable<T>> create(): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.areItemsTheSame(newItem)
            override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.areContentsTheSame(newItem)
        }
    }
}
