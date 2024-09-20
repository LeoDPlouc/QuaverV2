package quaverapp.quaver.dao.dto

import java.time.LocalDateTime

fun getMinimalV1ArtistDbDto() = ArtistDbDto(1, null, getMinimalV1CoverDbDto(), null, null, null)
fun getMaximalV1ArtistDbDto() = ArtistDbDto(
    2,
    "name",
    getMaximalV1CoverDbDto(),
    "mbid",
    LocalDateTime.of(2024, 7, 15, 10, 20),
    LocalDateTime.of(2024, 7, 15, 10, 20)
)
