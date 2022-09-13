package io.callibrity.labs.bakeoff.repository.entity

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class Artist(
    @field:Id
    @field:GeneratedValue(GeneratedValue.Type.AUTO)
    val id: Long? = null,
    val name: String,
    val genre: String)
