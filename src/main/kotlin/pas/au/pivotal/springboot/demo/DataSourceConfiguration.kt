package pas.au.pivotal.springboot.demo

import org.apache.commons.logging.LogFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.Cloud
import org.springframework.cloud.CloudFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

import javax.sql.DataSource

@Configuration
@Profile("cloud")
open class DataSourceConfiguration {

    @Bean
    open fun cloud(): Cloud {
        return CloudFactory().cloud
    }

    @Bean
    @ConfigurationProperties(DataSourceProperties.PREFIX)
    open fun dataSource(): DataSource {
        return cloud().getSingletonServiceConnector(DataSource::class.java, null)
    }

    companion object {
        private val logger = LogFactory.getLog(DataSourceConfiguration::class.java)
    }
}
