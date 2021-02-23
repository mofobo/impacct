package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Int>
{
    fun findAllByOwnerEmail(ownerEmail:String, pageable: Pageable): Page<Transaction>
}