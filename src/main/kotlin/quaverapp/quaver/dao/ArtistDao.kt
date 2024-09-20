package quaverapp.quaver.dao

import org.jooq.DSLContext
import org.jooq.generated.quaver.public.tables.references.ARTIST
import org.jooq.generated.quaver.public.tables.references.COVER
import org.jooq.impl.DSL.row
import org.springframework.stereotype.Repository
import quaverapp.quaver.dao.dto.ArtistDbDto
import quaverapp.quaver.dao.mapper.ArtistDbMapper
import quaverapp.quaver.model.Artist

@Repository
class ArtistDao(private val dsl: DSLContext, private val artistDbMapper: ArtistDbMapper) {
    fun getAllArtists(): List<Artist> {
        val dbResult = dsl.select(
            *ARTIST.fields(),
            row(*COVER.fields())
                .`as`("cover")
        )
            .from(ARTIST)
            .leftJoin(COVER)
            .on(ARTIST.COVER_ID.eq(COVER.ID))
            .fetch { record -> record.into(ArtistDbDto::class.java) }

        return dbResult.map(artistDbMapper::toModel)
    }

    fun getArtistById(id: Int): Artist? {
        val dbResult = dsl.select(
            *ARTIST.fields(),
            row(*COVER.fields())
                .`as`("cover")
        )
            .from(ARTIST)
            .leftJoin(COVER)
            .on(ARTIST.COVER_ID.eq(COVER.ID))
            .where(ARTIST.ID.eq(id))
            .fetchOne() { record -> record.into(ArtistDbDto::class.java) }

        return if (dbResult == null) null else artistDbMapper.toModel(dbResult)
    }
}