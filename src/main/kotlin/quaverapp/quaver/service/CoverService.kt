package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.service.exception.CoverNotFoundException
import java.util.Optional

@Service
class CoverService(private val coverDao: CoverDao) {

    @Throws(CoverNotFoundException::class)
    fun getById(id: Int) = Optional.ofNullable(coverDao.getById(id))
            .orElseThrow { CoverNotFoundException(id) }
}