package quaverapp.quaver.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import quaverapp.quaver.model.Cover

@SpringBootTest
class CoverControllerTest{

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @Sql(scripts = [
        "classpath:sql/create-schema.sql",
        "classpath:sql/cover.sql"
    ])
    fun should_get_cover_by_id() {
        var resultAsString = mockMvc.perform(get("/api/cover/1"))
            .andExpect(status().isOk())
            .andReturn()
            .response
            .contentAsString

        var result = objectMapper.readValue(resultAsString, Cover::class.java)


        resultAsString = mockMvc.perform(get("/api/cover/2"))
            .andExpect(status().isOk())
            .andReturn()
            .response
            .contentAsString

        result = objectMapper.readValue(resultAsString, Cover::class.java)

    }
}