package ru.zdanovich.developerslife.presentation.section.content

import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.developerslife.databinding.ItemDevLifePostBinding
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.extensions.loadGif

class DevLifePostViewHolder(
    private val binding: ItemDevLifePostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DevLifePost) = with(binding) {
        imageView.loadGif(
            item.gifUrl,
            item.previewURL
        )

        textViewDescription.text = item.description
    }
}