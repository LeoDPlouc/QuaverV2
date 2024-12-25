package quaverapp.quaver.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import quaverapp.quaver.model.Artist

@SpringBootTest
class ArtistControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @Sql(
        scripts = [
            "classpath:sql/create-schema.sql",
            "classpath:sql/cover.sql",
            "classpath:sql/artist.sql",
        ]
    )
    fun should_get_all_artists() {
        val resultAsString = mockMvc.perform(
            get("/api/artist")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        val result: List<Artist> = objectMapper.readValue(resultAsString)

    }

    @Test
    @Sql(
        scripts = [
            "classpath:sql/create-schema.sql",
            "classpath:sql/cover.sql",
            "classpath:sql/artist.sql",
        ]
    )
    fun getArtistById() {
        var resultAsString = mockMvc.perform(
            get("/api/artist/1")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        var result: Artist = objectMapper.readValue(resultAsString)


        resultAsString = mockMvc.perform(
            get("/api/artist/2")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        result = objectMapper.readValue(resultAsString)

    }

    @Test
    fun getSongsByArtistId() {
    }

    @Test
    fun getAlbumByArtistId() {
    }
}