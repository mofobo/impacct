package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long>