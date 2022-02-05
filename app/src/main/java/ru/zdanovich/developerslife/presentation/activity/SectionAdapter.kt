package ru.zdanovich.developerslife.presentation.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.presentation.section.SectionFragment

class SectionAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun createFragment(position: Int): Fragment {
        return SectionFragment.newInstance(itemList[position])
    }

    companion object {
        val itemList = SectionType.values()
    }
}