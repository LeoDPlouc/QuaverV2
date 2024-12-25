package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.COVER
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Cover
import java.util.Optional

@Repository
class CoverDao(private val dsl: DslProvider) {

    fun getById(id: Int): Cover? = dsl.selectCover()
        .where(COVER.ID.eq(id))
        .fetchOne { it.into(Cover::class.java) }
}