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
    override fun findById(id: String): Optional<Artist> {
        return repository.findById(id)
    }

    override fun findAll(): List<Artist> {
        return repository.findAll().toList()
    }

    override fun update(id: String, artist: ArtistRequest): Optional<Artist> {
        return repository.findById(id)
            .map {
                val updated = Artist(id, artist.name, artist.genre.genre)
                repository.update(updated)
                updated
            }
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun save(artist: ArtistRequest): Artist {
        return repository.save(Artist(name = artist.name, genre = artist.genre.genre))
    }
}