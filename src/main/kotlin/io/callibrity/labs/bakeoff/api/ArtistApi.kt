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

// TODO: add Docker support
@Controller("/api/artists")
open class ArtistApi(val service: ArtistService) {

    @Get()
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<ArtistResponse> {
        return service.findAll()
    }

    @Get(uri = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findById(@PathVariable(name = "id") id: Long): Optional<ArtistResponse> {
        return service.findById(id)
    }

    @Post()
    open fun save(@Valid @Body artist: ArtistRequest): HttpResponse<Long> {
        val id = service.save(artist)
        val uri = UriBuilder.of("/api/artists/${id}").build()
        return HttpResponse.created(uri)
    }

    @Put(uri = "/{id}")
    open fun update(@Valid @Body artist: ArtistRequest,
                    @PathVariable(name = "id") id: Long): HttpResponse<Long> {
        service.update(id, artist)
        return HttpResponse.noContent<Long?>().header(HttpHeaders.LOCATION, "/api/artists/${id}")
    }

    @Delete(uri = "{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable(name = "id") id: Long) {
        service.delete(id)
    }
}
