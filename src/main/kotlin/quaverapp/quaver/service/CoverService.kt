package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.service.exception.CoverNotFoundException

@Service
class CoverService(private val coverDao: CoverDao) {

    @Throws(CoverNotFoundException::class)
    fun getById(id: Int) = coverDao.getById(id)
            .orElseThrow { CoverNotFoundException(id) }
}