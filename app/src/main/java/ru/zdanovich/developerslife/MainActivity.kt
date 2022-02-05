package ru.zdanovich.developerslife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.developerslife.databinding.ActivityMainBinding
import ru.zdanovich.developerslife.extensions.viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}