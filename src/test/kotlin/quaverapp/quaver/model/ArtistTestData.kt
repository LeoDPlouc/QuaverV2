package quaverapp.quaver.model

import java.time.LocalDateTime

fun getMinimalV1Artist() = Artist(1, null, null, null, null, null)
fun getMaximalV1Artist() = Artist(
    2,
    "name",
    getMaximalV1Cover(),
    "mbid",
    LocalDateTime.of(2024, 7, 15, 10, 20),
    LocalDateTime.of(2024, 7, 15, 10, 20)
)

fun getAllArtists() = listOf(getMinimalV1Artist(), getMaximalV1Artist())