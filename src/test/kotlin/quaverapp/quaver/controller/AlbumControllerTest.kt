package quaverapp.quaver.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.get
import quaverapp.quaver.ControllerTest
import quaverapp.quaver.model.Album

class AlbumControllerTest : ControllerTest() {

    @Test
    fun `should return all albums`() {
        // Arrange
        val album = createAlbum()

        // Act
        val result = mockMvc.get("/api/album")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val actualAlbums = objectMapper.readValue<List<Album>>(result, listType(Album::class.java))

        assertThat(actualAlbums)
            .isEqualTo(listOf(album))
    }

    @Test
    fun `should return album by id`() {
        // Arrange
        val album = createAlbum()

        // Act
        val result = mockMvc.get("/api/album/${album.id}")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val actualAlbum = objectMapper.readValue(result, Album::class.java)
        assertThat(actualAlbum)
            .isEqualTo(album)
    }
}
