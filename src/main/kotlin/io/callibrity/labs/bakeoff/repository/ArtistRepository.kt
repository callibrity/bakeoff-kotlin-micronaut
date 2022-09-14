package io.callibrity.labs.bakeoff.repository

import io.callibrity.labs.bakeoff.repository.entity.Artist
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface ArtistRepository : PageableRepository<Artist, String>