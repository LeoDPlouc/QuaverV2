package quaverapp.quaver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quaverapp.quaver.service.SongService

@RestController
@RequestMapping("/api/song")
class SongController(private val songService: SongService) {

    @GetMapping
    fun getAllSong() = songService.getAllSongs();
}