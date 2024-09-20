package quaverapp.quaver.dao.mapper

import org.springframework.stereotype.Component
import quaverapp.quaver.dao.dto.ArtistDbDto
import quaverapp.quaver.model.Artist

@Component
class ArtistDbMapper(private val coverDbMapper: CoverDbMapper) {
    fun toModel(dto: ArtistDbDto) = Artist(
        dto.id!!,
        dto.name,
        coverDbMapper.toModel(dto.cover),
        dto.mbid,
        dto.createdAt,
        dto.updatedAt
    )
}