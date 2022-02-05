package ru.zdanovich.developerslife.domain.models


data class DevLifePost(
    val id: Int,
    val description: String,
    val gifUrl: String,
    val previewURL: String?,
): Similarable<DevLifePost> {
    override fun areItemsTheSame(other: DevLifePost) = this.id == other.id
    override fun areContentsTheSame(other: DevLifePost) = this == other
}