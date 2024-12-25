package quaverapp.quaver.dao

import org.assertj.core.api.Assertions
import org.jooq.generated.quaver.public.tables.references.COVER
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest
import quaverapp.quaver.model.Cover
import kotlin.test.assertNull

class CoverDaoTest : IntegrationTest() {

    @Autowired
    lateinit var coverDao: CoverDao

    var cover: Cover? = null

    @BeforeEach
    fun setUp() {
        cover = Cover(
            id = 1,
            tinyUrl = "http://example.com/tiny.jpg",
            smallUrl = "http://example.com/small.jpg",
            mediumUrl = "http://example.com/medium.jpg",
            largeUrl = "http://example.com/large.jpg",
            veryLargeUrl = "http://example.com/very_large.jpg",
        )

        dsl.insertInto(COVER)
            .set(COVER.ID, cover?.id)
            .set(COVER.TINY_URL, cover?.tinyUrl)
            .set(COVER.SMALL_URL, cover?.smallUrl)
            .set(COVER.MEDIUM_URL, cover?.mediumUrl)
            .set(COVER.LARGE_URL, cover?.largeUrl)
            .set(COVER.VERY_LARGE_URL, cover?.veryLargeUrl)
            .execute()
    }

    @Test
    fun `test getById returns a cover when found`() {
        // Act
        val result = coverDao.getById(1)

        // Assert
        Assertions.assertThat(result)
            .isEqualTo(cover)
    }

    @Test
    fun `test getById returns null when not found`() {
        // Act
        val result = coverDao.getById(999)

        // Assert
        assertNull(result)
    }
}
