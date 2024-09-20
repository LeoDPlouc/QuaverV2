package quaverapp.quaver.dao.mapper

import org.springframework.stereotype.Component
import quaverapp.quaver.dao.dto.CoverDbDto
import quaverapp.quaver.model.Cover

@Component
class CoverDbMapper {
    fun toModel(dto: CoverDbDto): Cover? {
        if(dto == CoverDbDto.empty()) return null

        return Cover(
            dto.id!!,
            dto.tinyUrl!!,
            dto.smallUrl,
            dto.mediumUrl,
            dto.largeUrl,
            dto.veryLargeUrl
        )
    }
}