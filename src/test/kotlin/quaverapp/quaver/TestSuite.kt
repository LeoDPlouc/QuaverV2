package quaverapp.quaver

import quaverapp.quaver.model.*
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger

abstract class TestSuite {

    private val coverId = AtomicInteger()
    private val artistId = AtomicInteger()
    private val joiningId = AtomicInteger()
    private val albumId = AtomicInteger()
    private val songId = AtomicInteger()

    open fun createCover(): Cover {
        return Cover(
            id = coverId.getAndIncrement(),
            tinyUrl = "http://cover.com/tiny",
            smallUrl = "http://cover.com/small",
            mediumUrl = "http://cover.com/medium",
            largeUrl = "http://cover.com/large",
            veryLargeUrl = "http://cover.com/veryLarge"
        )
    }

    open fun createArtist(): Artist {
        return Artist(
            id = artistId.getAndIncrement(),
            name = "name",
            cover = createCover(),
            mbid = "mbid",
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0)
        )
    }

    open fun createJoining(artistId: Int): Joining {
        return Joining(
            id = joiningId.getAndIncrement(),
            n = 1,
            artistId = artistId,
            joinphrase = "joinphrase"
        )
    }

    open fun createAlbum(): Album {
        val artist = createArtist()

        return Album(
            id = albumId.getAndIncrement(),
            title = "title",
            artists = listOf(artist),
            cover = createCover(),
            year = 2000,
            mbid = "album-mbid",
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            coverUpdatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            joinings = listOf(createJoining(artist.id))
        )
    }

    open fun createSong(): Song {
        val artist = createArtist()
        return Song(
            id = songId.getAndIncrement(),
            title = "title",
            n = 1,
            duration = 240,
            like = Like.Like,
            artists = listOf(artist),
            album = createAlbum(),
            path = "/music/song.mp3",
            acoustid = "acoustid",
            year = 2024,
            format = "mp3",
            mbid = "song-mbid",
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            lastUpdated = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            joinings = listOf(createJoining(artist.id))
        )
    }
}