package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.dao.AlbumDao
import quaverapp.quaver.service.exception.AlbumNotFoundException
import java.util.*

@Service
class AlbumService(val albumDao: AlbumDao) {
    fun getAllAlbum() = albumDao.getAllAlbums()

    @Throws(AlbumNotFoundException::class)
    fun getAlbumById(id: Int) = albumDao.getAlbumById(id) ?: throw AlbumNotFoundException(id)

    fun getAlbumsByArtistId(id: Int) = albumDao.getAlbumsByArtistId(id)
}