package quaverapp.quaver.dao

import org.jooq.Record
import org.jooq.generated.quaver.public.tables.references.SONG
import org.jooq.generated.quaver.public.tables.references.SONG_TO_ARTIST_LINK
import org.jooq.impl.DSL.field
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Song


@Repository
class SongDao(private val dsl: DslProvider) {
    fun getAllSongs(): List<Song> = dsl.selectSong()
        .fetch(::songMapper)

    fun getSongByArtistId(id: Int): List<Song> = dsl.selectSong()
        .leftJoin(SONG_TO_ARTIST_LINK)
        .on(SONG_TO_ARTIST_LINK.SONG_ID.eq(SONG.ID))
        .where(SONG_TO_ARTIST_LINK.ARTIST_ID.eq(id))
        .fetch { songMapper(it) }

    fun getSongFromAlbumById(id: Int): List<Song> = dsl.selectSong()
        .where(SONG.ALBUM_ID.eq(id))
        .fetch { songMapper(it) }

    private fun songMapper(record: Record): Song? {
        record.set(field("album"), (record.get("album") as List<*>).first())
        return record.into(Song::class.java)
    }
}