package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import quaverapp.quaver.model.Cover
import quaverapp.quaver.model.getMinimalV1Cover

@SpringBootTest
class CoverDaoTest{

    @Autowired
    private lateinit var coverDao: CoverDao

    @Test
    @Sql(scripts = [
        "classpath:sql/create-schema.sql",
        "classpath:sql/cover.sql"
    ])
    fun should_get_cover() {
        val cover = coverDao.getById(1)

        assertThat(cover).isNotEmpty()
        assertThat(cover).contains(getMinimalV1Cover())
    }

}