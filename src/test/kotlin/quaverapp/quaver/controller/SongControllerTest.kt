package quaverapp.quaver.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.get
import quaverapp.quaver.ControllerTest
import quaverapp.quaver.model.Song

class SongControllerTest : ControllerTest() {

    @Test
    fun `should return all songs`() {
        // Arrange
        val song = createSong()

        // Act
        val resultAsString = mockMvc.get("/api/song")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        // Assert
        val result = objectMapper.readValue<List<Song>>(resultAsString, listType(Song::class.java))

        Assertions.assertThat(result)
            .isEqualTo(listOf(song))
    }
}
