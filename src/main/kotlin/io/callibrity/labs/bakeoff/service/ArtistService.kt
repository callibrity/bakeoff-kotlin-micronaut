package io.callibrity.labs.bakeoff.service

import io.callibrity.labs.bakeoff.model.ArtistRequest
import io.callibrity.labs.bakeoff.model.ArtistResponse
import java.util.*

interface ArtistService {

    fun findById(id: Long): Optional<ArtistResponse>

    fun findAll(): List<ArtistResponse>

    fun update(id: Long, artist: ArtistRequest)

    fun delete(id: Long)

    fun save(artist: ArtistRequest): Long?
}