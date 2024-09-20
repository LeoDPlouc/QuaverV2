package quaverapp.quaver.service.exception

class AlbumNotFoundException(albumId: Int) : Exception("Album not found for id=$albumId") {
}