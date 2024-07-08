package quaverapp.quaver.service

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.internal.util.Supplier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.model.getMinimalV1Cover
import quaverapp.quaver.service.exception.CoverNotFoundException
import java.util.*
import org.mockito.Mockito.`when` as mockitoWhen

@SpringBootTest
class CoverServiceTest {

    @Mock
    private lateinit var coverDao: CoverDao

    @InjectMocks
    private lateinit var coverService: CoverService

    @Test
    fun should_get_cover_by_id() {
        mockitoWhen(coverDao.getById(1)).thenReturn(Optional.ofNullable(getMinimalV1Cover()))

        assertThat(coverService.getById(1)).isEqualTo(getMinimalV1Cover());
    }

    @Test
    fun should_throw_when_getCoverById_return_optional_empty() {
        mockitoWhen(coverDao.getById(1)).thenReturn(Optional.empty());

        assertThrows<CoverNotFoundException> { coverService.getById(1)}
    }
}