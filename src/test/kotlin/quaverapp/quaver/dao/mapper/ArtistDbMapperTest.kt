package quaverapp.quaver.dao.mapper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import quaverapp.quaver.dao.dto.getMaximalV1ArtistDbDto
import quaverapp.quaver.dao.dto.getMaximalV1CoverDbDto
import quaverapp.quaver.dao.dto.getMinimalV1ArtistDbDto
import quaverapp.quaver.dao.dto.getMinimalV1CoverDbDto
import quaverapp.quaver.model.getMaximalV1Artist
import quaverapp.quaver.model.getMaximalV1Cover
import quaverapp.quaver.model.getMinimalV1Artist
import org.mockito.Mockito.`when` as mockitoWhen

@SpringBootTest
class ArtistDbMapperTest {

    @Mock
    private lateinit var coverDbMapper: CoverDbMapper
    @InjectMocks
    private lateinit var mapper: ArtistDbMapper

    @Test
    fun should_map_to_model() {
        mockitoWhen(coverDbMapper.toModel(getMinimalV1CoverDbDto())).thenReturn(null)
        var result = mapper.toModel(getMinimalV1ArtistDbDto())
        assertThat(result).isEqualTo(getMinimalV1Artist())

        mockitoWhen(coverDbMapper.toModel(getMaximalV1CoverDbDto())).thenReturn(getMaximalV1Cover())
        result = mapper.toModel(getMaximalV1ArtistDbDto())
        assertThat(result).isEqualTo(getMaximalV1Artist());
    }
}