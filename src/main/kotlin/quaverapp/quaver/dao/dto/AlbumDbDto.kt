package quaverapp.quaver.dao.dto

import java.time.LocalDateTime

data class AlbumDbDto(
    val id: UInt?,
    val title: String?,
    val artists: List<ArtistDbDto>?,
    val cover: CoverDbDto?,
    val year: UInt?,
    val mbid: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val coverUpdatedAt: LocalDateTime?,
    val joinings: List<JoiningDbDto>?
) {
    companion object {
        fun empty() = AlbumDbDto(null, null, null, null, null, null, null, null, null, null)
    }
}
