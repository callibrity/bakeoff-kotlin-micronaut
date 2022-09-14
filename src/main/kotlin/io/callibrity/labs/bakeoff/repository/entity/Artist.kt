package io.callibrity.labs.bakeoff.repository.entity

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@MappedEntity
data class Artist(
    @field:Id
    val id: String? = UUID.randomUUID().toString(),
    val name: String,
    val genre: String)
