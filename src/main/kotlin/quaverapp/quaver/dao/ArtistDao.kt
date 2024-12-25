package quaverapp.quaver.dao

import org.jooq.generated.quaver.public.tables.references.ARTIST
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Artist

@Repository
class ArtistDao(private val dsl: DslProvider) {
    fun getAllArtists(): List<Artist> = dsl.selectArtist()
        .fetch { it.into(Artist::class.java) }


    fun getArtistById(id: Int) = dsl.selectArtist()
        .where(ARTIST.ID.eq(id))
        .fetchOne { it.into(Artist::class.java) }

}