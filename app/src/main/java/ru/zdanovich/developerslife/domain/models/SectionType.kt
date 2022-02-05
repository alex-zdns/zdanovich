package ru.zdanovich.developerslife.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SectionType : Parcelable {
    LATEST,
    HOT,
    TOP
}