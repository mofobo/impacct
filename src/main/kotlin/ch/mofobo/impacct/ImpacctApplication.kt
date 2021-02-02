package ch.mofobo.impacct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class ImpacctApplication

fun main(args: Array<String>) {
    runApplication<ImpacctApplication>(*args)
}
