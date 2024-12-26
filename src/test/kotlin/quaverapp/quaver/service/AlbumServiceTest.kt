import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import quaverapp.quaver.UnitTest
import quaverapp.quaver.dao.AlbumDao
import quaverapp.quaver.service.AlbumService
import quaverapp.quaver.service.exception.AlbumNotFoundException

class AlbumServiceTest : UnitTest() {

    @Mock
    private lateinit var albumDao: AlbumDao

    @InjectMocks
    private lateinit var albumService: AlbumService

    @Test
    fun `should return all albums`() {
        // Arrange
        val albums = listOf(
            createAlbum(),
            createAlbum()
        )
        `when`(albumDao.getAllAlbums()).thenReturn(albums)

        // Act
        val result = albumService.getAllAlbum()

        // Assert
        assertThat(result)
            .isEqualTo(albums)
    }

    @Test
    fun `should return album by id when it exists`() {
        // Arrange
        val album = createAlbum()
        `when`(albumDao.getAlbumById(1)).thenReturn(album)

        // Act
        val result = albumService.getAlbumById(1)

        // Assert
        assertThat(result)
            .isEqualTo(album)
    }

    @Test
    fun `should throw AlbumNotFoundException when album does not exist by id`() {
        // Arrange
        `when`(albumDao.getAlbumById(1)).thenReturn(null)

        // Act
        val thrown = catchException { albumService.getAlbumById(1) }

        // Assert
        assertThat(thrown).isInstanceOf(AlbumNotFoundException::class.java)
    }

    @Test
    fun `should return albums by artist id`() {
        // Arrange
        val albums = listOf(
            createAlbum(),
            createAlbum()
        )
        `when`(albumDao.getAlbumsByArtistId(1)).thenReturn(albums)

        // Act
        val result = albumService.getAlbumsByArtistId(1)

        // Assert
        assertThat(result)
            .isEqualTo(albums)
    }
}
