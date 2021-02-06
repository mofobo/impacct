package ch.mofobo.impacct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("ch.mofobo.impacct.entities")
@EnableJpaRepositories("ch.mofobo.impacct.repositories")
class ImpacctApplication

fun main(args: Array<String>) {
    runApplication<ImpacctApplication>(*args)
}
