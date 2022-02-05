package ru.zdanovich.developerslife.presentation.activity

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import ru.zdanovich.developerslife.databinding.ActivityMainBinding
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.extensions.getUiName
import ru.zdanovich.developerslife.extensions.loadGif
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

    private fun setupView() {
        binding.viewPager.adapter = sectionAdapter

        com.google.android.material.tabs.TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, pos ->
            tab.text = tabsTitles[pos]
        }.attach()
    }
}