package quaverapp.quaver.service

import org.springframework.stereotype.Service
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.model.Cover
import quaverapp.quaver.service.exception.CoverNotFoundException
import java.util.Optional

@Service
class CoverService(private val coverDao: CoverDao) {

    @Throws(CoverNotFoundException::class)
    fun getById(id: Int): Cover = coverDao.getById(id) ?: throw CoverNotFoundException(id)
}