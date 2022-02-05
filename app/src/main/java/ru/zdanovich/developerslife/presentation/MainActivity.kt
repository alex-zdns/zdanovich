package ru.zdanovich.developerslife.presentation

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}