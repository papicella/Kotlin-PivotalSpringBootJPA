package pas.au.pivotal.springboot.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class PivotalSpringBootJpaApplication

fun main(args: Array<String>) {
    SpringApplication.run(PivotalSpringBootJpaApplication::class.java, *args)
}


