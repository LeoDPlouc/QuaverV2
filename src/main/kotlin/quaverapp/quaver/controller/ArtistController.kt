package quaverapp.quaver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quaverapp.quaver.service.AlbumService
import quaverapp.quaver.service.ArtistService
import quaverapp.quaver.service.SongService

@RestController
@RequestMapping("/api/artist")
class ArtistController(
    private val artistService: ArtistService,
    private val songService: SongService,
    private val albumService: AlbumService
) {
    @GetMapping
    fun getAllArtists() = artistService.getAllArtists()

    @GetMapping("/{id}")
    fun getArtistById(@PathVariable id: Int) = artistService.getArtistById(id)

    @GetMapping("/{id}/songs")
    fun getSongsByArtistId(@PathVariable id: Int) = songService.getSongByArtistId(id)

    @GetMapping("/{id}/albums")
    fun getAlbumByArtistId(@PathVariable id: Int) = albumService.getAlbumsByArtistId(id);
}