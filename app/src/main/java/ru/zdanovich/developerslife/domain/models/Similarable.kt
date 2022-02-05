package ru.zdanovich.developerslife.domain.models

interface Similarable<T> {
    fun areItemsTheSame(other: T): Boolean
    fun areContentsTheSame(other: T): Boolean
}
