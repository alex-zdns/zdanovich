package ru.zdanovich.developerslife.presentation.activity

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.presentation.section.SectionFragment

class SectionAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    companion object {
        val itemList = SectionType.values()
    }

    override fun getItemCount() = itemList.size
    override fun createFragment(position: Int) = SectionFragment.newInstance(itemList[position])
}