package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.model.Song
import quaverapp.quaver.dao.SongDao

@Service
class SongService(private val songDao: SongDao) {
    fun getAllSongs(): List<Song> {
        return songDao.fetchAllSongs()
    }
}