package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.*
import org.jooq.impl.DSL
import org.jooq.impl.DSL.multiset
import org.jooq.impl.DSL.row
import org.springframework.stereotype.Component
import quaverapp.quaver.model.Album
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Joining

@Component
class DslProvider(val dsl: DSLContext) {
    fun selectArtist() = dsl.select(
        *ARTIST.fields(),
        row(*COVER.fields())
            .`as`("cover")
    )
        .from(ARTIST)
        .leftJoin(COVER)
        .on(ARTIST.COVER_ID.eq(COVER.ID))

    fun selectAlbum() = dsl.select(
        *ALBUM.fields(),
        row(*COVER.fields())
            .`as`("cover"),
        multiset(selectArtist())
            .`as`("artists")
            .convertFrom { it.into(Artist::class.java) },
        multiset(selectAlbumJoining())
            .`as`("joinings")
            .convertFrom { it.into(Joining::class.java) }
    )
        .from(ALBUM)
        .leftJoin(COVER)
        .on(ALBUM.COVER_ID.eq(COVER.ID))

    fun selectAlbumJoining() = dsl.select(*JOINING.fields())
        .from(JOINING)
        .leftJoin(ALBUM_TO_JOINING_LINK)
        .on(ALBUM_TO_JOINING_LINK.JOINING_ID.eq(JOINING.ID))

    fun selectCover() = dsl.select(*COVER.fields())
        .from(COVER)

    fun selectSong() = dsl.select(
        *SONG.fields(),
        multiset(selectAlbum())
            .`as`("album")
            .convertFrom { it.into(Album::class.java) },
        multiset(selectArtist())
            .`as`("artists")
            .convertFrom { it.into(Artist::class.java) },
        multiset(selectSongJoining())
            .`as`("joinings")
            .convertFrom { it.into(Joining::class.java) }
    )
        .from(SONG)
        .leftJoin(ALBUM)
        .on(ALBUM.ID.eq(SONG.ALBUM_ID))


    fun selectSongJoining() = dsl.select(*JOINING.fields())
        .from(JOINING)
        .leftJoin(SONG_TO_JOINING_LINK)
        .on(SONG_TO_JOINING_LINK.JOINING_ID.eq(JOINING.ID))
}