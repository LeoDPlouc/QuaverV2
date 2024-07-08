package quaverapp.quaver.model

data class Cover(
    val id: Int,
    val tinyUrl: String,
    val smallUrl: String?,
    val mediumUrl: String?,
    val largeUrl: String?,
    val veryLargeUrl: String?
)