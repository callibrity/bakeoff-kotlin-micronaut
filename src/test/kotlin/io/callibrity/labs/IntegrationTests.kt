package io.callibrity.labs;

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.callibrity.labs.bakeoff.model.ArtistRequest
import io.callibrity.labs.bakeoff.model.ArtistResponse
import io.callibrity.labs.bakeoff.model.Genre
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@MicronautTest
class IntegrationTest {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient
    private val mapper = jacksonObjectMapper()
    private var endpoint: String = "/api/artists"

    @Test
    @DisplayName("test the findAll endpoint")
    fun test1() {
        val expectedAsJson: String = IntegrationTest::class.java.getResource("/responses/artists.all.json").readText(Charsets.UTF_8)
        val request: HttpRequest<Any> = HttpRequest.GET(endpoint)
        val response: HttpResponse<List<ArtistResponse>> = client.toBlocking().exchange(request, Argument.listOf(ArtistResponse::class.java))
        val expected: List<ArtistResponse> = mapper.readValue(expectedAsJson)

        assertEquals(HttpStatus.OK, response.getStatus())
        assertEquals(expected, response.body())
    }

    @Test
    @DisplayName("test the find endpoint")
    fun test2() {
        val expectedAsJson: String = IntegrationTest::class.java.getResource("/responses/artist.1.json").readText(Charsets.UTF_8)
        val request: HttpRequest<Any> = HttpRequest.GET("${endpoint}/1")
        val response: HttpResponse<ArtistResponse> = client.toBlocking().exchange(request, Argument.of(ArtistResponse::class.java))
        val expected: ArtistResponse = mapper.readValue(expectedAsJson)

        assertEquals(HttpStatus.OK, response.getStatus())
        assertEquals(expected, response.body())

        val request2: HttpRequest<Any> = HttpRequest.GET("${endpoint}/100")
        val ex: HttpClientResponseException = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().exchange(request2, String::class.java)
        }
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus())
    }

    @Test
    @DisplayName("save an artist, update the artist, delete the artist")
    fun test3() {
        val requestCreate: HttpRequest<Any> = HttpRequest.POST(endpoint, ArtistRequest("David Bowie", Genre.Pop))
        val responseCreated: HttpResponse<ArtistResponse> = client.toBlocking().exchange(requestCreate, Argument.of(ArtistResponse::class.java))
        val artistEndpoint = responseCreated.header(HttpHeaders.LOCATION)

        assertEquals(HttpStatus.OK, responseCreated.getStatus())
        assertNotNull(artistEndpoint)

        // empty request body -- this will fail validation and a 400 BAD REQUEST status code will be returned
        val requestCreateBadRequest: HttpRequest<Any> = HttpRequest.POST(endpoint, "{}")
        val exCreatedFailed: HttpClientResponseException = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().exchange(requestCreateBadRequest, String::class.java)
        }
        assertEquals(HttpStatus.BAD_REQUEST, exCreatedFailed.getStatus())

        val requestUpdate: HttpRequest<Any> = HttpRequest.PUT(artistEndpoint, ArtistRequest("David Bowie, the angel", Genre.Western))
        val responseUpdated: HttpResponse<ArtistResponse> = client.toBlocking().exchange(requestUpdate, Argument.of(ArtistResponse::class.java))

        assertEquals(HttpStatus.OK, responseUpdated.status)

        val requestGetUpdatedArtist: HttpRequest<Any> = HttpRequest.GET(artistEndpoint)
        val responseGetUpdatedArtist: HttpResponse<ArtistResponse> = client.toBlocking().exchange(requestGetUpdatedArtist, Argument.of(ArtistResponse::class.java))

        assertEquals("David Bowie, the angel", responseGetUpdatedArtist.body()?.name)
        assertEquals(Genre.Western.genre, responseGetUpdatedArtist.body()?.genre)

        val requestDelete: HttpRequest<Any> = HttpRequest.DELETE(artistEndpoint)
        val responseDeleted: HttpResponse<ArtistResponse> = client.toBlocking().exchange(requestDelete)

        assertEquals(HttpStatus.OK, responseDeleted.getStatus())
    }
}