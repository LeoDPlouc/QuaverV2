package quaverapp.quaver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuaverApplication

fun main(args: Array<String>) {
	runApplication<QuaverApplication>(*args)
}
