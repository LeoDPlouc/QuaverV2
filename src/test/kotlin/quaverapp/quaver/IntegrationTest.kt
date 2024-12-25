package quaverapp.quaver

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.*
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.WaitStrategy

@SpringBootTest
@ContextConfiguration(initializers = [IntegrationTest.Initializer::class])
@AutoConfigureMockMvc
abstract class IntegrationTest {
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
}