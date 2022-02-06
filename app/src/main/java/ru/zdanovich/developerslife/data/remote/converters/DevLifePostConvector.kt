package ru.zdanovich.developerslife.data.remote.converters

import ru.zdanovich.developerslife.data.remote.MappingException
import ru.zdanovich.developerslife.data.remote.models.DevLifePostResponse
import ru.zdanovich.developerslife.domain.models.DevLifePost
import javax.inject.Inject

class DevLifePostConvector @Inject constructor() {
    fun convert(source: DevLifePostResponse) = DevLifePost(
        id = source.id?:throw MappingException(DevLifePost::id.name),
        description = source.description?:throw MappingException(DevLifePost::description.name),
        gifUrl = source.gifUrl?:throw MappingException(DevLifePost::description.name)
    )
}