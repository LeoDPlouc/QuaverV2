package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.dao.SongDao
import quaverapp.quaver.model.Song

@Service
class SongService(private val songDao: SongDao) {
    fun getAllSongs(): List<Song> {
        return songDao.fetchAllSongs()
    }

    fun getSongByArtistId(id: Int) = songDao.getSongByArtistId(id)

    fun getSongByAlbumId(id: Int) = songDao.getSongFromAlbumById(id)
}