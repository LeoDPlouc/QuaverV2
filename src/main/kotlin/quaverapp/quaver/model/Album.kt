package quaverapp.quaver.model

import java.time.LocalDateTime

data class Album(
    val id: UInt,
    val title: String,
    val artists: List<Artist>,
    val cover: Cover,
    val year: UInt,
    val mbid: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val coverUpdatedAt: LocalDateTime,
    val joinings: List<Joining>
)
