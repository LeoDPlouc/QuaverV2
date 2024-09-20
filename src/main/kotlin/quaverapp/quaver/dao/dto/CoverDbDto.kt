package quaverapp.quaver.dao.dto

data class CoverDbDto(
    val id: Int?,
    val tinyUrl: String?,
    val smallUrl: String?,
    val mediumUrl: String?,
    val largeUrl: String?,
    val veryLargeUrl: String?
) {
    companion object {
        fun empty() = CoverDbDto(null, null, null, null, null, null)
    }
}