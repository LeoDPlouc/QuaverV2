package quaverapp.quaver.model

import java.time.LocalDateTime

data class Song(
    val id: Int,
    val title: String,
    val n: Int,
    val duration: Int,
    val like: Like = Like.Nothing,
    val artists: List<Artist>,
    val album: Album,
    val path: String,
    val acoustid: String,
    val year: Int,
    val format: String,
    val mbid: String,
    val createdAt: LocalDateTime,
    val lastUpdated: LocalDateTime,
    val joinings: List<Joining>
    )

enum class Like {
    Like, Nothing, Dislike
}
