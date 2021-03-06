package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.Category
import ch.mofobo.impacct.entities.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.YearMonth

@Repository
interface TransactionRepository : JpaRepository<Transaction, Int> {
    fun findAllByOwnerEmail(ownerEmail: String, pageable: Pageable): Page<Transaction>
    fun findAllByCategoryAndDateBetweenAndType(category: Category, year1: YearMonth, year2: YearMonth, type: String): MutableList<Transaction>

    fun findAllByTypeAndDate(type: String, yearMonth: YearMonth): MutableList<Transaction>

    @Query("SELECT trx.date from transactions trx")
    fun getAllDates(): MutableList<YearMonth>?
}