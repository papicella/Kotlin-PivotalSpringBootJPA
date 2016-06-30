package pas.au.pivotal.springboot.demo.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pas.au.pivotal.springboot.demo.domain.Album

interface JpaAlbumRepository : JpaRepository<Album, String> {

    @Query("select a from Album a where a.title like %?1%")
    fun findByTitleContains(title: String) : List<Album>

    fun findByReleaseYear(releaseYear: String): Album
}