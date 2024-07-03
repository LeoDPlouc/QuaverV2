package quaverapp.quaver

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import quaverapp.quaver.model.Like
import quaverapp.quaver.model.Song

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(QuaverApplication::class.java)
	}

}
