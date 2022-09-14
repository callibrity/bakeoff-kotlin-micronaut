package io.callibrity.labs.bakeoff.service

import io.callibrity.labs.bakeoff.model.ArtistRequest
import io.callibrity.labs.bakeoff.model.ArtistResponse
import io.callibrity.labs.bakeoff.repository.entity.Artist
import java.util.*

interface ArtistService {

    fun findById(id: String): Optional<Artist>

    fun findAll(): List<Artist>

    fun update(id: String, artist: ArtistRequest): Optional<Artist>

    fun delete(id: String)

    fun save(artist: ArtistRequest): Artist
}