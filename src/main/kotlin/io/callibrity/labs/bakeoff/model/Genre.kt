package io.callibrity.labs.bakeoff.model

import io.micronaut.core.annotation.Introspected

@Introspected
enum class Genre(val genre: String) {
    ROCK("rock"),
    POP("pop"),
    COUNTRY("country"),
    WESTERN("western")
}