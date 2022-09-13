package io.callibrity.labs.bakeoff.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class ArtistResponse(
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long? = null,
    val name: String,
    val genre: String
)
