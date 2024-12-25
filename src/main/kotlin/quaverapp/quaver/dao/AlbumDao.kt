package quaverapp.quaver.dao

import org.jooq.generated.quaver.public.tables.references.ALBUM
import org.jooq.generated.quaver.public.tables.references.ALBUM_TO_ARTIST_LINK
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Album

@Repository
class AlbumDao(
    val dsl: DslProvider,
) {
    fun getAllAlbums(): List<Album> = dsl.selectAlbum()
        .fetch { it.into(Album::class.java) }

    fun getAlbumById(id: Int): Album? = dsl.selectAlbum()
        .where(ALBUM.ID.eq(id))
        .fetchOne { it.into(Album::class.java) }

    fun getAlbumsByArtistId(id: Int): List<Album> = dsl.selectAlbum()
        .leftJoin(ALBUM_TO_ARTIST_LINK)
        .on(ALBUM_TO_ARTIST_LINK.ALBUM_ID.eq(ALBUM.ID))
        .where(ALBUM_TO_ARTIST_LINK.ARTIST_ID.eq(id))
        .fetch { it.into(Album::class.java) }
}