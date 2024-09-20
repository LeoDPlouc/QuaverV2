package quaverapp.quaver.dao.mapper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import quaverapp.quaver.dao.dto.getMaximalV1CoverDbDto
import quaverapp.quaver.dao.dto.getMinimalV1CoverDbDto
import quaverapp.quaver.model.getMaximalV1Cover

@SpringBootTest
class CoverDbMapperTest {

    @Autowired
    private lateinit var mapper: CoverDbMapper

    @Test
    fun should_map_to_model() {
        var result = mapper.toModel(getMinimalV1CoverDbDto())
        assertThat(result).isNull()

        result = mapper.toModel(getMaximalV1CoverDbDto())
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(getMaximalV1Cover())
    }
}