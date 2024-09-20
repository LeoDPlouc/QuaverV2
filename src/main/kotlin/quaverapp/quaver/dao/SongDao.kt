package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.SONG
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Song

@Repository
class SongDao(private val dsl: DSLContext) {
    fun fetchAllSongs(): List<Song> {
        return getSelectDsl()
            .fetch { it.into(Song::class.java) }
    }

    fun getSongByArtistId(id: Int): List<Song> {
        return getSelectDsl()
            .where(SONG.songToArtistLink().ARTIST_ID.eq(id))
            .fetch { it.into(Song::class.java) }
    }

    fun getSongFromAlbumById(id: Int): List<Song> {
        return getSelectDsl()
            .where(SONG.ALBUM_ID.eq(id))
            .fetch { it.into(Song::class.java) }
    }

    private fun getSelectDsl() = dsl.select(*SONG.fields())
        .from(SONG)
}