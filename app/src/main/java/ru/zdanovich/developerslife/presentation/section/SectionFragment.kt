package ru.zdanovich.developerslife.presentation.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import ru.zdanovich.developerslife.R
import ru.zdanovich.developerslife.databinding.FragmentSectionBinding
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.extensions.viewBinding
import ru.zdanovich.developerslife.presentation.paging.PagingLoadStateAdapter
import ru.zdanovich.developerslife.presentation.section.content.DevLifePostAdapter
import javax.inject.Inject

@AndroidEntryPoint
class SectionFragment: androidx.fragment.app.Fragment(R.layout.fragment_section) {
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
        viewPager.adapter = devLifePostAdapter.apply {
            withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter { devLifePostAdapter.retry() },
                footer = PagingLoadStateAdapter { devLifePostAdapter.retry() }
            )
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
    }

    private fun onBindViewModel() {
        viewModel.postsLiveData.observe(this.viewLifecycleOwner, this::setPosts)
    }

    private fun setPosts(data: PagingData<DevLifePost>){
        devLifePostAdapter.submitData(lifecycle, data)
    }
}