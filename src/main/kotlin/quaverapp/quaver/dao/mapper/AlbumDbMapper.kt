package quaverapp.quaver.dao.mapper

import org.springframework.stereotype.Component
import quaverapp.quaver.dao.dto.AlbumDbDto
import quaverapp.quaver.model.Album

@Component
class AlbumDbMapper(
    val artistDbMapper: ArtistDbMapper,
    val coverDbMapper: CoverDbMapper,
    val joiningDbMapper: JoiningDbMapper
) {
    fun toModel(dto: AlbumDbDto): Album? {
        if(dto == AlbumDbDto.empty()) return null

        return Album(
            dto.id!!,
            dto.title,
            dto.artists?.map { artistDbMapper.toModel(it) } ?: emptyList(),
            dto.cover?.let { coverDbMapper.toModel(it) },
            dto.year,
            dto.mbid,
            dto.createdAt!!,
            dto.updatedAt,
            dto.coverUpdatedAt,
            dto.joinings?.map { joiningDbMapper.toModel(it) } ?: emptyList()
        )
    }
}