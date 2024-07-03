package quaverapp.quaver.model

import java.time.LocalDateTime

data class Song(
    val id: UInt,
    val title: String,
    val n: UInt,
    val duration: UInt,
    val like: Like = Like.Nothing,
    val artists: List<String>,
    val album: String,
    val path: String,
    val acoustid: String,
    val year: Int,
    val format: String,
    val mbid: String,
    val lastUpdated: LocalDateTime,
    val joinings: List<Joining>
    )

enum class Like {
    Like, Nothing, Dislike
}

data class Joining(
    val id: ULong,
    val joinhrase: String
)
