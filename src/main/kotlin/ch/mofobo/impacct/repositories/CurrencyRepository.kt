package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.Currency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository : JpaRepository<Currency, Long>