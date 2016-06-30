package pas.au.pivotal.springboot.demo.controllers

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import pas.au.pivotal.springboot.demo.domain.Album
import pas.au.pivotal.springboot.demo.repositories.JpaAlbumRepository

import kotlin.*

@Controller
class AlbumController
@Autowired
constructor(private val repository: JpaAlbumRepository) {

    @RequestMapping(value = "/albumsslow", method = arrayOf(RequestMethod.GET))
    @Throws(Exception::class)
    fun listProductsSlow(model: Model): String {

        // Sleep for 6 seconds before returning to simulate slow page
        Thread.sleep(6000)

        model.addAttribute("albums", repository.findAll())
        model.addAttribute("count", repository.findAll().size)
        model.addAttribute("countStr", String.format("Total of %s albums", repository.findAll().size))
        return "albums"
    }

    @RequestMapping(value = "/albums", method = arrayOf(RequestMethod.GET))
    fun listProducts(model: Model): String {

        val findAll = repository.findAll()
        model.addAttribute("albums", findAll)
        model.addAttribute("count", findAll.size)
        model.addAttribute("countStr", String.format("Total of %s albums", findAll.size))
        return "albums"
    }

    @RequestMapping(value = "/deletealbum", method = arrayOf(RequestMethod.GET))
    fun deleteAlbum(@RequestParam("id") id: String, model: Model): String {
        repository.delete(id)
        val actionStr = String.format("Album [%s] successfully deleted", id)

        val albums = repository.findAll()

        model.addAttribute("actionStr", actionStr)
        model.addAttribute("albums", albums)
        model.addAttribute("count", albums.size)
        model.addAttribute("countStr", String.format("Total of %s albums", albums.size))
        return "albums"
    }

    @RequestMapping(value = "/addnew", method = arrayOf(RequestMethod.GET))
    fun addNewAlbum(model: Model): String {
        val album = Album()
        model.addAttribute("album", album)
        return "newalbum"
    }

    @RequestMapping(value = "/editalbum", method = arrayOf(RequestMethod.GET))
    fun editAlbum(@RequestParam(value = "id", required = true) id: String, model: Model): String {
        val album = repository.findOne(id)
        model.addAttribute("album", album)
        return "editalbum"
    }

    @RequestMapping(value = "/searchAlbums", method = arrayOf(RequestMethod.POST))
    fun searchAlbums(@RequestParam(value = "title") title: String, model: Model): String {
        val albums = repository.findByTitleContains(title)
        model.addAttribute("title", title)
        model.addAttribute("albums", albums)
        model.addAttribute("count", albums.size)
        model.addAttribute("countStr", String.format("Total of %s albums", albums.size))

        return "albums"
    }

    @RequestMapping(value = "/addAlbum", method = arrayOf(RequestMethod.POST))
    fun addAlbum(@RequestParam(value = "title") title: String,
                 @RequestParam(value = "artist") artist: String,
                 @RequestParam(value = "releaseYear") releaseYear: String,
                 model: Model): String {

        val album = Album(title, artist, releaseYear)
        repository.save(album)

        val actionStr = "Album successfully added"

        val albums = repository.findAll()
        model.addAttribute("actionStr", actionStr)
        model.addAttribute("albums", albums)
        model.addAttribute("count", albums.size)
        model.addAttribute("countStr", String.format("Total of %s albums", albums.size))

        return "albums"
    }

    @RequestMapping(value = "/editAlbum", method = arrayOf(RequestMethod.POST))
    fun editAlbum(@RequestParam(value = "title") title: String,
                  @RequestParam(value = "artist") artist: String,
                  @RequestParam(value = "releaseYear") releaseYear: String,
                  @RequestParam(value = "id", required = true) id: String,
                  model: Model): String {

        val album = Album(title, artist, releaseYear)
        album.id = id
        repository.save(album)

        val actionStr = String.format("Album [%s] successfully edited", id)

        val albums = repository.findAll()
        model.addAttribute("actionStr", actionStr)
        model.addAttribute("albums", albums)
        model.addAttribute("count", albums.size)
        model.addAttribute("countStr", String.format("Total of %s albums", albums.size))

        return "albums"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AlbumController::class.java)
    }
}
