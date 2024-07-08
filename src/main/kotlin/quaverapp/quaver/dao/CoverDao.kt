package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.COVER
import org.springframework.stereotype.Repository
import quaverapp.quaver.model.Cover
import java.util.Optional

@Repository
class CoverDao(private val dsl: DSLContext) {
    fun getById(id: Int): Optional<Cover> {
        val cover = dsl.select(*COVER.fields())
            .from(COVER)
            .where(COVER.ID.eq(id))
            .fetchOne()
            ?.into(Cover::class.java)

        return Optional.ofNullable(cover)
    }
}