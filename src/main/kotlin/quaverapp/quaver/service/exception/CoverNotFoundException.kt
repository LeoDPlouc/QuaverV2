package quaverapp.quaver.service.exception

class CoverNotFoundException(coverId: Int) : Exception("Cover not found for id=$coverId") {
}