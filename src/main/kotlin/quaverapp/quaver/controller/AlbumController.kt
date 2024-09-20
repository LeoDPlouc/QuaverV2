package quaverapp.quaver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quaverapp.quaver.service.AlbumService;
import quaverapp.quaver.service.SongService

@RestController
@RequestMapping("/api/album")
class AlbumController(
    val albumService : AlbumService,
    val songService: SongService
) {

    @GetMapping
    fun getAllAlbum() = albumService.getAllAlbum()

    @GetMapping("/{id}")
    fun getAlbumById(@PathVariable id: Int) = albumService.getAlbumById(id)

    @GetMapping("/{id}/songs")
    fun getSongByAlbumId(@PathVariable id: Int) = songService.getSongByAlbumId(id)
}
