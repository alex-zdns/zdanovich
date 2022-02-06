package ru.zdanovich.developerslife.presentation.paging

import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.developerslife.databinding.ItemErrorBinding

class PagingErrorStateViewHolder(
    binding: ItemErrorBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.textViewRetry.setOnClickListener { retry.invoke() }
    }
}