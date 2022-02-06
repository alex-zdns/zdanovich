package ru.zdanovich.developerslife.presentation.section

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.customview.widget.ViewDragHelper
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.zdanovich.developerslife.R
import ru.zdanovich.developerslife.databinding.FragmentSectionBinding
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.LoadableResult
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.extensions.viewBinding
import ru.zdanovich.developerslife.presentation.paging.PagingLoadStateAdapter
import ru.zdanovich.developerslife.presentation.section.content.DevLifePostAdapter
import javax.inject.Inject

@AndroidEntryPoint
class SectionFragment : androidx.fragment.app.Fragment(R.layout.fragment_section) {
    companion object {
        private const val SECTION_TYPE = "DEV_LIFE_SECTION_TYPE"

        fun newInstance(type: SectionType): SectionFragment =
            SectionFragment().apply {
                val args = Bundle()
                args.putParcelable(SECTION_TYPE, type)
                arguments = args
            }
    }

    private val binding by viewBinding { FragmentSectionBinding.bind(it) }
    private val viewModel: SectionViewModel by viewModels()

    @Inject
    lateinit var devLifePostAdapter: DevLifePostAdapter

    private val defaultLoadingState: (CombinedLoadStates) -> Unit = { loadState ->
        viewModel.bindPagingState(loadState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<SectionType>(SECTION_TYPE)?.let { type ->
            viewModel.type = type
        }

        callOperation()
        setupView()
        onBindViewModel()
    }

    private fun callOperation() = viewModel.launchPagingData()

    private fun setupView() = with(binding) {
        viewPager.adapter = devLifePostAdapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { devLifePostAdapter.retry() },
            footer = PagingLoadStateAdapter { devLifePostAdapter.retry() }
        )

        stateViewFlipper.setRetryMethod { viewModel.launchPagingData() }

        devLifePostAdapter.addLoadStateListener(defaultLoadingState)

        devLifePostAdapter.addOnPagesUpdatedListener {
            val itemCount = devLifePostAdapter.itemCount
            imageViewNextButton.isEnabled = itemCount > 0
            setVisibleEmptyView(itemCount == 0)
        }

        imageViewNextButton.setOnClickListener {
            var currentItem = viewPager.currentItem
            viewPager.currentItem = ++currentItem
        }

        imageViewBackButton.setOnClickListener {
            var currentItem = viewPager.currentItem

            if (currentItem != 0) {
                viewPager.currentItem = --currentItem
            }
        }

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    imageViewBackButton.isEnabled = position != 0
                }
            }
        )

        imageViewBackButton.isEnabled = false
        imageViewNextButton.isEnabled = false
    }

    private fun onBindViewModel() {
        viewModel.postsLiveData.observe(this.viewLifecycleOwner, this::setPosts)
        viewModel.postPagingStateLiveData.observe(this.viewLifecycleOwner, this::showStatus)
    }

    private fun setPosts(data: PagingData<DevLifePost>) {
        devLifePostAdapter.submitData(lifecycle, data)
    }

    private fun showStatus(result: LoadableResult<Unit>) {
        binding.stateViewFlipper.setStateFromResult(result)
    }

    private fun setVisibleEmptyView(isVisible: Boolean) = with(binding) {
        emptyView.isVisible = isVisible
        viewPager.isVisible = !isVisible
    }
}