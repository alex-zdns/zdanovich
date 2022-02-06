package ru.zdanovich.developerslife.domain.models

class PageInfo<T> (
    val items: List<T>,
    val totalCount: Int
)