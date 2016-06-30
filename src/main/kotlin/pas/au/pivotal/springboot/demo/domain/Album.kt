package pas.au.pivotal.springboot.demo.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import kotlin.*

@Entity
data class Album (var title: String = "",var artist: String="",var releaseYear: String="")
{
    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "randomId")
    @GenericGenerator(name = "randomId", strategy = "pas.au.pivotal.springboot.demo.domain.RandomIdGenerator")
    lateinit var id: String
}