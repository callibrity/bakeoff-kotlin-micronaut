package io.callibrity.labs.bakeoff.model

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class ArtistRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val genre: Genre)
