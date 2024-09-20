package quaverapp.quaver.service;

import org.springframework.stereotype.Service;
import quaverapp.quaver.dao.ArtistDao
import quaverapp.quaver.model.Artist
import quaverapp.quaver.service.exception.ArtistNotFoundException
import java.util.Optional

@Service
class ArtistService(val artistDao: ArtistDao) {
    fun getAllArtists() = artistDao.getAllArtists()

    @Throws(ArtistNotFoundException::class)
    fun getArtistById(id: Int): Artist = Optional.ofNullable(artistDao.getArtistById(id))
        .orElseThrow { ArtistNotFoundException(id) }
}
