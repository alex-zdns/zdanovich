package ru.zdanovich.developerslife.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ru.zdanovich.developerslife.databinding.ActivityMainBinding
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.extensions.getUiName
import ru.zdanovich.developerslife.extensions.viewBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val tabsTitles by lazy { SectionType.values().map { resources.getString(it.getUiName())} }

    private val sectionAdapter by lazy { SectionAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() = with(binding) {
        viewPager.apply {
            adapter = sectionAdapter
            isUserInputEnabled = false
        }

        com.google.android.material.tabs.TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, pos ->
            tab.text = tabsTitles[pos]
        }.attach()
    }
}