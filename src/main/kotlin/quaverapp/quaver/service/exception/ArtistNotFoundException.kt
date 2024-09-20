package quaverapp.quaver.service.exception

class ArtistNotFoundException(coverId: Int) : Exception("Artist not found for id=$coverId") {
}