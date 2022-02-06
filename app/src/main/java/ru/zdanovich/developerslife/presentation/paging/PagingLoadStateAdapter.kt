package ru.zdanovich.developerslife.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.developerslife.databinding.ItemErrorBinding
import ru.zdanovich.developerslife.databinding.ItemLoadingBinding

class PagingLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {}

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        when (loadState) {
            is LoadState.Error -> createLoadingViewHolder(parent)
            is LoadState.Loading -> createErrorViewHolder(parent)
            else -> createErrorViewHolder(parent)
        }


    private fun createLoadingViewHolder(parent: ViewGroup): PagingLoadingStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingLoadingStateViewHolder(binding)
    }

    private fun createErrorViewHolder(parent: ViewGroup): PagingErrorStateViewHolder {
        val binding = ItemErrorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingErrorStateViewHolder(binding, retry)
    }
}
