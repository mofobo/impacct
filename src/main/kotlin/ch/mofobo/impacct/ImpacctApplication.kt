package ch.mofobo.impacct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@SpringBootApplication
@EntityScan("ch.mofobo.impacct.entities")
@EnableJpaRepositories("ch.mofobo.impacct.repositories")
@ComponentScan
class ImpacctApplication

fun main(args: Array<String>) {
    runApplication<ImpacctApplication>(*args)
}
