package pas.au.pivotal.springboot.demo.controllers

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import pas.au.pivotal.springboot.demo.repositories.JpaAlbumRepository

@Controller
class WelcomeController
@Autowired
constructor(private val repository: JpaAlbumRepository) {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun welcome(model: Model): String {
        model.addAttribute("count", repository.findAll().size)
        return "welcome"
    }

    @RequestMapping(value = "/killme", method = kotlin.arrayOf(RequestMethod.GET))
    @Throws(Exception::class)
    fun properties(model: Model): String {
        Runtime.getRuntime().halt(-1)
        return "welcome"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AlbumController::class.java)
    }

}
