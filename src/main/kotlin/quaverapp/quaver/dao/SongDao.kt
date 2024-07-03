package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.SONG
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Song

@Repository
class SongDao(private val dsl: DSLContext) {
    fun fetchAllSongs(): List<Song> {
        return dsl.select(*SONG.fields())
            .from(SONG)
            .fetch()
            .into(Song::class.java)
    }
}