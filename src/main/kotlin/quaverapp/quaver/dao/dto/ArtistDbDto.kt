package quaverapp.quaver.dao.dto

import java.time.LocalDateTime

data class ArtistDbDto(
    val id: Int?,
    val name: String?,
    val cover: CoverDbDto,
    val mbid: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
