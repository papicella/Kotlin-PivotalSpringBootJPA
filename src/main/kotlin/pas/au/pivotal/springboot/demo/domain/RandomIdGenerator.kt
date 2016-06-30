package pas.au.pivotal.springboot.demo.domain

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.id.IdentifierGenerator

import java.io.Serializable
import java.util.UUID

class RandomIdGenerator : IdentifierGenerator {
    @Throws(HibernateException::class)
    override fun generate(session: SessionImplementor, `object`: Any): Serializable {
        return generateId()
    }

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}
