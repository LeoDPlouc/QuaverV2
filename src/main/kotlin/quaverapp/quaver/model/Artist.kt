package quaverapp.quaver.model

import java.time.LocalDateTime

data class Artist(
    val id: UInt,
    val name: String,
    val cover: Cover,
    val mbid: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
