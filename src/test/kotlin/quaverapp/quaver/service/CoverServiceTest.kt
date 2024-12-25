package quaverapp.quaver.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.service.exception.CoverNotFoundException
import org.mockito.Mockito.`when` as mockitoWhen

@SpringBootTest
class CoverServiceTest {

    @Mock
    private lateinit var coverDao: CoverDao

    @InjectMocks
    private lateinit var coverService: CoverService

    @Test
    fun should_get_cover_by_id() {
    }

    @Test
    fun should_throw_when_getCoverById_return_optional_empty() {
        mockitoWhen(coverDao.getById(1)).thenReturn(null);

        assertThrows<CoverNotFoundException> { coverService.getById(1)}
    }
}