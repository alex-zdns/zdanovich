package ru.zdanovich.developerslife.extensions

import androidx.annotation.StringRes
import ru.zdanovich.developerslife.R
import ru.zdanovich.developerslife.domain.models.SectionType

@StringRes
fun SectionType.getUiName() = when(this) {
    SectionType.LATEST -> R.string.latest
    SectionType.HOT -> R.string.hot
    SectionType.TOP -> R.string.top
}