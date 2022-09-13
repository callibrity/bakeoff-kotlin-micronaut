package io.callibrity.labs.bakeoff.service.impl

import io.callibrity.labs.bakeoff.model.ArtistRequest
import io.callibrity.labs.bakeoff.model.ArtistResponse
import io.callibrity.labs.bakeoff.repository.entity.Artist
import io.callibrity.labs.bakeoff.repository.ArtistRepository
import io.callibrity.labs.bakeoff.service.ArtistService
import jakarta.inject.Singleton
import java.util.*

@Singleton
class ArtistServiceImpl(val repository: ArtistRepository) : ArtistService {
    override fun findById(id: Long): Optional<ArtistResponse> {
        return repository.findById(id).map { ArtistResponse(it.id, it.name, it.genre) }
    }

    override fun findAll(): List<ArtistResponse> {
        return repository.findAll().map { ArtistResponse(it.id, it.name, it.genre) }
    }

    override fun update(id: Long, artist: ArtistRequest) {
        repository.update(Artist(id, artist.name, artist.genre.genre))
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }

    override fun save(artist: ArtistRequest): Long? {
        return repository.save(Artist(name = artist.name, genre = artist.genre.genre)).id
    }
}