package quaverapp.quaver.dao.mapper

import org.springframework.stereotype.Component
import quaverapp.quaver.dao.dto.JoiningDbDto
import quaverapp.quaver.model.Joining

@Component
class JoiningDbMapper {
    fun toModel(dto: JoiningDbDto): Joining {
        return Joining(
            dto.id!!,
            dto.n!!,
            dto.artistId!!,
            dto.joinphrase!!
        )
    }
}
