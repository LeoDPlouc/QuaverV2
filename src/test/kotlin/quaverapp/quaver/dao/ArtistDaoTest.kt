package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.jooq.generated.quaver.public.tables.references.ARTIST
import org.jooq.generated.quaver.public.tables.references.COVER
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Cover
import java.time.LocalDateTime

class ArtistDaoTest : IntegrationTest() {

    @Autowired
    lateinit var artistDao: ArtistDao

    var artist: Artist? = null

    @BeforeEach
    fun setUp() {
        artist = Artist(
            id = 1,
            name = "Artist 1",
            cover = Cover(
                id = 1,
                tinyUrl = "http://example.com/tiny.jpg",
                smallUrl = "http://example.com/small.jpg",
                mediumUrl = "http://example.com/medium.jpg",
                largeUrl = "http://example.com/large.jpg",
                veryLargeUrl = "http://example.com/very_large.jpg",
            ),
            mbid = "mbid",
            createdAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 10, 10, 0, 0, 0),
        )

        dsl.insertInto(COVER)
            .set(COVER.ID, artist?.cover?.id)
            .set(COVER.TINY_URL, artist?.cover?.tinyUrl)
            .set(COVER.SMALL_URL, artist?.cover?.smallUrl)
            .set(COVER.MEDIUM_URL, artist?.cover?.mediumUrl)
            .set(COVER.LARGE_URL, artist?.cover?.largeUrl)
            .set(COVER.VERY_LARGE_URL, artist?.cover?.veryLargeUrl)
            .execute()

        dsl.insertInto(ARTIST)
            .set(ARTIST.ID, artist?.id)
            .set(ARTIST.NAME, artist?.name)
            .set(ARTIST.COVER_ID, artist?.cover?.id)
            .set(ARTIST.MBID, artist?.mbid)
            .set(ARTIST.CREATED_AT, artist?.createdAt)
            .set(ARTIST.UPDATED_AT, artist?.updatedAt)
            .execute()
    }

    @Test
    fun `test getAllArtists returns a list of artists`() {
        // Act
        val result = artistDao.getAllArtists()

        // Assert
        assertThat(result)
            .isEqualTo(listOf(artist))
    }

    @Test
    fun `test getArtistById returns an artist when found`() {
        // Act
        val result = artistDao.getArtistById(1)

        // Assert
        assertThat(result)
            .isEqualTo(artist)
    }

    @Test
    fun `test getArtistById returns null when not found`() {
        // Act
        val result = artistDao.getArtistById(999)

        // Assert
        assertThat(result).isNull()
    }
}
