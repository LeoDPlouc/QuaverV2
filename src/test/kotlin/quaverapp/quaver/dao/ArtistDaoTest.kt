package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import quaverapp.quaver.model.getAllArtists
import quaverapp.quaver.model.getMaximalV1Artist
import quaverapp.quaver.model.getMinimalV1Artist

@SpringBootTest
class ArtistDaoTest {

    @Autowired
    private lateinit var artistDao: ArtistDao

    @Test
    @Sql(
        scripts = [
            "classpath:sql/create-schema.sql",
            "classpath:sql/cover.sql",
            "classpath:sql/artist.sql",
        ]
    )
    fun should_get_all_Artists() {
        val artistResults = artistDao.getAllArtists()

        assertThat(artistResults).isNotEmpty
        assertThat(artistResults).isEqualTo(getAllArtists())
    }

    @Test
    @Sql(
        scripts = [
            "classpath:sql/create-schema.sql",
            "classpath:sql/cover.sql",
            "classpath:sql/artist.sql",
        ]
    )
    fun should_get_artist_by_id() {
        var artistResult = artistDao.getArtistById(1)

        assertThat(artistResult).isNotNull()
        assertThat(artistResult).isEqualTo(getMinimalV1Artist());

        artistResult = artistDao.getArtistById(2)

        assertThat(artistResult).isNotNull()
        assertThat(artistResult).isEqualTo(getMaximalV1Artist());
    }
}