package io.callibrity.labs.bakeoff.api

import io.callibrity.labs.bakeoff.model.ArtistRequest
import io.callibrity.labs.bakeoff.model.ArtistResponse
import io.callibrity.labs.bakeoff.service.ArtistService
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import java.util.*
import javax.validation.Valid

@Controller("/api/artists")
open class ArtistApi(val service: ArtistService) {

    @Get()
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<ArtistResponse> {
        return service.findAll()
            .map { ArtistResponse(it.id, it.name, it.genre) }
    }

    @Get(uri = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findById(@PathVariable(name = "id") id: String): HttpResponse<ArtistResponse> {
        return service.findById(id)
            .map { ArtistResponse(it.id, it.name, it.genre) }
            .map { HttpResponse.ok(it) }
            .orElse(HttpResponse.notFound())
    }

    @Post()
    open fun save(@Valid @Body artist: ArtistRequest): HttpResponse<ArtistResponse> {
        val saved = service.save(artist)
        return HttpResponse.ok(ArtistResponse(saved.id, saved.name, saved.genre)).header(HttpHeaders.LOCATION, "/api/artists/${saved.id}")
    }

    @Put(uri = "/{id}")
    open fun update(@Valid @Body artist: ArtistRequest,
                    @PathVariable(name = "id") id: String): HttpResponse<ArtistResponse> {
        return service.update(id, artist)
            .map { ArtistResponse(it.id, it.name, it.genre)  }
            .map { HttpResponse.ok(it).header(HttpHeaders.LOCATION, "/api/artists/${id}") }
            .orElse(HttpResponse.notFound())
    }

    @Delete(uri = "{id}")
    fun delete(@PathVariable(name = "id") id: String) {
        service.delete(id)
    }
}
