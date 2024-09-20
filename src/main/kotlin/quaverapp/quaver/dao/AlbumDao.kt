package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.ALBUM
import org.jooq.generated.quaver.public.tables.references.ARTIST
import org.jooq.generated.quaver.public.tables.references.COVER
import org.jooq.impl.DSL.row
import org.springframework.stereotype.Repository
import quaverapp.quaver.dao.dto.AlbumDbDto
import quaverapp.quaver.dao.mapper.AlbumDbMapper
import quaverapp.quaver.model.Album

@Repository
class AlbumDao(
    val dsl: DSLContext,
    val albumDbMapper: AlbumDbMapper
) {
    fun getAllAlbums(): List<Album> {
        return getSelectDsl()
            .fetch { it.into(AlbumDbDto::class.java) }
            .mapNotNull(albumDbMapper::toModel)
    }

    fun getAlbumById(id: Int): Album? {
        return getSelectDsl()
            .where(ALBUM.ID.eq(id))
            .fetchOne { it.into(AlbumDbDto::class.java) }
            ?.let(albumDbMapper::toModel)
    }

    fun getAlbumsByArtistId(id: Int): List<Album> {
        return getSelectDsl()
            .where(ALBUM.albumToArtistLink().ARTIST_ID.eq(id))
            .fetch { it.into(AlbumDbDto::class.java) }
            .mapNotNull(albumDbMapper::toModel)
    }

    private fun getSelectDsl() = dsl.select(
        *ALBUM.fields(),
        row(*COVER.fields())
            .`as`("cover"),
        row(*ARTIST.fields())
            .`as`("artist")
    )
        .from(ALBUM)
        .leftJoin(COVER)
        .on(ALBUM.COVER_ID.eq(COVER.ID))
}