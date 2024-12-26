package quaverapp.quaver

import com.fasterxml.jackson.core.type.TypeReference
import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.*
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import quaverapp.quaver.model.*

@SpringBootTest
@ContextConfiguration(initializers = [IntegrationTest.Initializer::class])
abstract class IntegrationTest : TestSuite() {
    companion object {
        val pgsqlContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16")
            .withUsername("user")
            .withPassword("password")
            .withExposedPorts(5432)
            .withDatabaseName("quaver")
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            pgsqlContainer.start()


            TestPropertyValues.of(
                "spring.datasource.url=jdbc:postgresql://${pgsqlContainer.host}:${pgsqlContainer.getMappedPort(5432)}/${pgsqlContainer.databaseName}",
                "spring.datasource.username=${pgsqlContainer.username}",
                "spring.datasource.password=${pgsqlContainer.password}",
                "spring.datasource.driver-class-name=org.postgresql.Driver",
                "spring.flyway.locations=classpath:db/migration",
            ).applyTo(applicationContext.environment)
        }
    }

    @Autowired
    lateinit var dsl: DSLContext

    @AfterEach
    fun tearDown() {
        dsl.deleteFrom(ALBUM_TO_ARTIST_LINK)
        dsl.deleteFrom(ALBUM_TO_JOINING_LINK)
        dsl.deleteFrom(SONG_TO_JOINING_LINK)
        dsl.deleteFrom(SONG_TO_ARTIST_LINK)
        dsl.deleteFrom(SONG).execute()
        dsl.deleteFrom(ALBUM).execute()
        dsl.deleteFrom(ARTIST).execute()
        dsl.deleteFrom(COVER).execute()
    }

    fun insertCover(cover: Cover?) {
        dsl.insertInto(COVER)
            .set(COVER.ID, cover?.id)
            .set(COVER.TINY_URL, cover?.tinyUrl)
            .set(COVER.SMALL_URL, cover?.smallUrl)
            .set(COVER.MEDIUM_URL, cover?.mediumUrl)
            .set(COVER.LARGE_URL, cover?.largeUrl)
            .set(COVER.VERY_LARGE_URL, cover?.veryLargeUrl)
            .execute()
    }

    fun insertAlbum(album: Album?) {
        dsl.insertInto(ALBUM)
            .set(ALBUM.ID, album?.id)
            .set(ALBUM.TITLE, album?.title)
            .set(ALBUM.YEAR, album?.year)
            .set(ALBUM.MBID, album?.mbid)
            .set(ALBUM.CREATED_AT, album?.createdAt)
            .set(ALBUM.UPDATED_AT, album?.updatedAt)
            .set(ALBUM.COVER_UPDATED_AT, album?.coverUpdatedAt)
            .set(ALBUM.COVER_ID, album?.cover?.id)
            .execute()
    }

    fun insertJoining(joining: Joining?) {
        dsl.insertInto(JOINING)
            .set(JOINING.ID, joining?.id)
            .set(JOINING.N, joining?.n)
            .set(JOINING.ARTIST_ID, joining?.artistId)
            .set(JOINING.JOINPHRASE, joining?.joinphrase)
            .execute()
    }

    fun insertArtist(artist: Artist?) {
        dsl.insertInto(ARTIST)
            .set(ARTIST.ID, artist?.id)
            .set(ARTIST.NAME, artist?.name)
            .set(ARTIST.MBID, artist?.mbid)
            .set(ARTIST.CREATED_AT, artist?.createdAt)
            .set(ARTIST.UPDATED_AT, artist?.updatedAt)
            .set(ARTIST.COVER_ID, artist?.cover?.id)
            .execute()
    }

    fun insertSong(song: Song?) {
        dsl.insertInto(SONG)
            .set(SONG.ID, song?.id)
            .set(SONG.TITLE, song?.title)
            .set(SONG.ALBUM_ID, song?.album?.id)
            .set(SONG.LIKE, song?.like)
            .set(SONG.DURATION, song?.duration)
            .set(SONG.N, song?.n)
            .set(SONG.PATH, song?.path)
            .set(SONG.ACOUSTID, song?.acoustid)
            .set(SONG.YEAR, song?.year)
            .set(SONG.FORMAT, song?.format)
            .set(SONG.MBID, song?.mbid)
            .set(SONG.CREATED_AT, song?.createdAt)
            .set(SONG.LAST_UPDATED, song?.lastUpdated)
            .set(SONG.ALBUM_ID, song?.album?.id)
            .execute()
    }

    fun insertSongArtistLink(song: Song?, artist: Artist?) {
        dsl.insertInto(SONG_TO_ARTIST_LINK)
            .set(SONG_TO_ARTIST_LINK.SONG_ID, song?.id)
            .set(SONG_TO_ARTIST_LINK.ARTIST_ID, song?.id)
            .execute()
    }

    fun insertSongJoiningLink(song: Song?, joining: Joining?) {
        dsl.insertInto(SONG_TO_JOINING_LINK)
            .set(SONG_TO_JOINING_LINK.SONG_ID, song?.id)
            .set(SONG_TO_JOINING_LINK.JOINING_ID, joining?.id)
            .execute()
    }

    fun insertAlbumArtistLink(album: Album?, artist: Artist?) {
        dsl.insertInto(ALBUM_TO_ARTIST_LINK)
            .set(ALBUM_TO_ARTIST_LINK.ARTIST_ID, artist?.id)
            .set(ALBUM_TO_ARTIST_LINK.ALBUM_ID, album?.id)
            .execute()
    }

    fun insertAlbumJoiningLink(album: Album?, joining: Joining?) {
        dsl.insertInto(ALBUM_TO_JOINING_LINK)
            .set(ALBUM_TO_JOINING_LINK.ALBUM_ID, album?.id)
            .set(ALBUM_TO_JOINING_LINK.JOINING_ID, joining?.id)
            .execute()
    }

    override fun createCover() = super.createCover()
        .apply(::insertCover)

    override fun createArtist(): Artist = super.createArtist()
        .apply(::insertArtist)

    override fun createJoining(artistId: Int): Joining = super.createJoining(artistId)
        .apply(::insertJoining)

    override fun createAlbum(): Album = super.createAlbum()
        .apply(::insertAlbum)
        .apply {
            artists.forEach { insertAlbumArtistLink(this, it) }
            joinings.forEach { insertAlbumJoiningLink(this, it) }
        }

    override fun createSong(): Song = super.createSong()
        .apply(::insertSong)
        .apply {
            artists.forEach { insertSongArtistLink(this, it) }
            joinings.forEach { insertSongJoiningLink(this, it) }
        }
}