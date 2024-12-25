package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.jooq.generated.quaver.public.tables.references.ALBUM
import org.jooq.generated.quaver.public.tables.references.ALBUM_TO_ARTIST_LINK
import org.jooq.generated.quaver.public.tables.references.ARTIST
import org.jooq.generated.quaver.public.tables.references.COVER
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest
import quaverapp.quaver.model.Album
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Cover
import java.time.LocalDateTime

class AlbumDaoTest : IntegrationTest() {

    @Autowired
    private lateinit var albumDao: AlbumDao

    var album: Album? = null
    var artist: Artist? = null

    @BeforeEach
    fun setUp() {
        artist = Artist(
            id = 1,
            mbid = "mbid",
            cover = Cover(
                id = 2,
                tinyUrl = "http://example.com/tiny.jpg",
                smallUrl = "http://example.com/small.jpg",
                mediumUrl = "http://example.com/medium.jpg",
                largeUrl = "http://example.com/large.jpg",
                veryLargeUrl = "http://example.com/very_large.jpg",
            ),
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            name = "name"
        )

        album = Album(
            id = 1,
            mbid = "mbid",
            cover = Cover(
                id = 1,
                tinyUrl = "http://example.com/tiny.jpg",
                smallUrl = "http://example.com/small.jpg",
                mediumUrl = "http://example.com/medium.jpg",
                largeUrl = "http://example.com/large.jpg",
                veryLargeUrl = "http://example.com/very_large.jpg",
            ),
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            year = 2000,
            title = "title",
            joinings = emptyList(),
            coverUpdatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            artists = listOf(artist!!),
        )

        dsl.insertInto(COVER)
            .set(COVER.ID, artist?.cover?.id)
            .set(COVER.TINY_URL, artist?.cover?.tinyUrl)
            .set(COVER.SMALL_URL, artist?.cover?.smallUrl)
            .set(COVER.MEDIUM_URL, artist?.cover?.mediumUrl)
            .set(COVER.LARGE_URL, artist?.cover?.largeUrl)
            .set(COVER.VERY_LARGE_URL, artist?.cover?.veryLargeUrl)
            .execute()

        dsl.insertInto(COVER)
            .set(COVER.ID, album?.cover?.id)
            .set(COVER.TINY_URL, album?.cover?.tinyUrl)
            .set(COVER.SMALL_URL, album?.cover?.smallUrl)
            .set(COVER.MEDIUM_URL, album?.cover?.mediumUrl)
            .set(COVER.LARGE_URL, album?.cover?.largeUrl)
            .set(COVER.VERY_LARGE_URL, album?.cover?.veryLargeUrl)
            .execute()

        dsl.insertInto(ARTIST)
            .set(ARTIST.ID, artist?.id)
            .set(ARTIST.NAME, artist?.name)
            .set(ARTIST.COVER_ID, artist?.cover?.id)
            .set(ARTIST.MBID, artist?.mbid)
            .set(ARTIST.CREATED_AT, artist?.createdAt)
            .set(ARTIST.UPDATED_AT, artist?.updatedAt)
            .execute()

        dsl.insertInto(ALBUM)
            .set(ALBUM.ID, album?.id)
            .set(ALBUM.TITLE, album?.title)
            .set(ALBUM.MBID, album?.mbid)
            .set(ALBUM.COVER_ID, album?.cover?.id)
            .set(ALBUM.CREATED_AT, album?.createdAt)
            .set(ALBUM.UPDATED_AT, album?.updatedAt)
            .set(ALBUM.COVER_UPDATED_AT, album?.coverUpdatedAt)
            .set(ALBUM.YEAR, album?.year)
            .execute()

        dsl.insertInto(ALBUM_TO_ARTIST_LINK)
            .set(ALBUM_TO_ARTIST_LINK.ALBUM_ID, album?.id)
            .set(ALBUM_TO_ARTIST_LINK.ARTIST_ID, artist?.id)
            .execute()
    }

    @Test
    fun `test getAllAlbums returns a list of albums with cover and artist`() {
        // Act
        val result = albumDao.getAllAlbums()

        // Assert
        assertThat(result)
            .isEqualTo(listOf(album))
    }

    @Test
    fun `test getAlbumById returns an album with cover and artist when found`() {
        // Act
        val result = albumDao.getAlbumById(1)

        // Assert
        assertThat(result)
            .isEqualTo(album)
    }

    @Test
    fun `test getAlbumById returns null when album not found`() {
        // Act
        val result = albumDao.getAlbumById(999)

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `test getAlbumsByArtistId returns albums related to an artist`() {
        // Act
        val result = albumDao.getAlbumsByArtistId(1)

        // Assert
        assertThat(result)
            .isEqualTo(listOf(album))
    }
}
