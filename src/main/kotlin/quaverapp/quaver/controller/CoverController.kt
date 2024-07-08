package quaverapp.quaver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quaverapp.quaver.service.CoverService

@RestController
@RequestMapping("api/cover")
class CoverController(private val coverService: CoverService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int) = coverService.getById(id)
}
