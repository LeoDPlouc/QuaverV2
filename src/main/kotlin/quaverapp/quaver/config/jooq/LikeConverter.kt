package quaverapp.quaver.config.jooq

import org.jooq.generated.quaver.public.enums.LikeEnum
import org.jooq.impl.AbstractConverter
import quaverapp.quaver.model.Like

class LikeConverter : AbstractConverter<LikeEnum, Like>(LikeEnum::class.java, Like::class.java) {
    override fun from(databaseObject: LikeEnum?): Like = databaseObject
        ?.let(LikeEnum::name)
        ?.let(String::lowercase)
        ?.replaceFirstChar(Char::uppercase)
        ?.let(Like::valueOf) ?: Like.Nothing

    override fun to(userObject: Like?): LikeEnum = userObject
        ?.let(Like::name)
        ?.let(String::uppercase)
        ?.let(LikeEnum::valueOf) ?: LikeEnum.NOTHING
}