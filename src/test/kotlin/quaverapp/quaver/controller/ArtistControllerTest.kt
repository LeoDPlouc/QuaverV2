package quaverapp.quaver.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.get
import quaverapp.quaver.ControllerTest
import quaverapp.quaver.model.Album
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Song

class ArtistControllerTest : ControllerTest() {

    @Test
    fun `should return all artists`() {
        // Arrange
        val artists = listOf(createArtist())

        // Act
        val resultAsString = mockMvc.get("/api/artist")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val result = objectMapper.readValue<List<Artist>>(resultAsString, listType(Artist::class.java))

        assertThat(result)
            .isEqualTo(artists)
    }


    @Test
    fun `should return artist by id`() {
        // Arrange
        val artist = createArtist()

        // Act
        val resultAsString = mockMvc.get("/api/artist/${artist.id}")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val result = objectMapper.readValue(resultAsString, Artist::class.java)

        assertThat(result)
            .isEqualTo(artist)
    }

    @Test
    fun `should return songs by artist id`() {
        // Arrange
        val songs = listOf(createSong())

        // Act
        val resultAsString = mockMvc.get("/api/artist/${songs.first().artists.first().id}/songs")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val result = objectMapper.readValue<List<Song>>(resultAsString, listType(Song::class.java))

        assertThat(result)
            .isEqualTo(songs)
    }

    @Test
    fun `should return albums by artist id`() {
        // Arrange
        val album = createAlbum()

        // Act
        val resultAsString = mockMvc.get("/api/artist/${album.artists.first().id}/albums")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val result = objectMapper.readValue<List<Album>>(resultAsString, listType(Album::class.java))

        assertThat(result)
            .isEqualTo(listOf(album))

    }
}
