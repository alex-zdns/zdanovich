package ru.zdanovich.developerslife.presentation.section.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.zdanovich.developerslife.databinding.ItemDevLifePostBinding
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.presentation.recyclerview.DiffUtilItemCallbackFactory
import javax.inject.Inject

class DevLifePostAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : ListAdapter<DevLifePost, DevLifePostViewHolder>(diffUtilItemCallbackFactory.create()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevLifePostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDevLifePostBinding.inflate(inflater, parent, false)
        return DevLifePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevLifePostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}