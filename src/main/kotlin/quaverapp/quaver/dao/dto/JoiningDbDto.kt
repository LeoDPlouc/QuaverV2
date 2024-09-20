package quaverapp.quaver.dao.dto

data class JoiningDbDto(
    val id: ULong?,
    val n: UInt?,
    val artistId: ULong?,
    val joinphrase: String?
) {
    companion object {
        fun empty() = JoiningDbDto(null, null, null, null)
    }
}