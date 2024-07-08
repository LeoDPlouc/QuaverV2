package quaverapp.quaver

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Configuration
class QuaverApplicationTests {

	@Bean
	fun mockMvc(context: WebApplicationContext) = MockMvcBuilders.webAppContextSetup(context).build()

}
