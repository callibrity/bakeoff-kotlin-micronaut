package io.callibrity.labs.bakeoff.model

import io.micronaut.core.annotation.Introspected

@Introspected
enum class Genre(val genre: String) {
    Rock("rock"),
    Pop("pop"),
    Country("country"),
    Western("western")
}