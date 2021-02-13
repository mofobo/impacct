package ch.mofobo.impacct.services

import ch.mofobo.impacct.dtos.TransactionCreationDto
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.repositories.CurrencyRepository
import ch.mofobo.impacct.repositories.SubcategoryRepository
import ch.mofobo.impacct.repositories.TransactionRepository
import ch.mofobo.impacct.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class TransactionService(
        private val transactionRepository: TransactionRepository,
        private val userRepository: UserRepository,
        private val subcategoryRepository: SubcategoryRepository,
        private val currencyRepository: CurrencyRepository) {

    fun getRecords(): MutableList<Transaction> = transactionRepository.findAll()
//
//    fun addRecord(task: Transaction): ResponseEntity<Transaction> =
//            ResponseEntity.ok(transactionRepository.save(task))
//
//    fun getRecordById(taskId: Long): ResponseEntity<Transaction> =
//            transactionRepository.findById(taskId).map { task ->
//                ResponseEntity.ok(task)
//            }.orElse(ResponseEntity.notFound().build())
//
//    fun putRecord(taskId: Long, newTransaction: Transaction): ResponseEntity<Transaction> = transactionRepository.findById(taskId).map { currentRecord ->
//        val updatedRecord = Transaction(
//                title = newTransaction.title,
//                description = newTransaction.description,
//                value = newTransaction.value,
//                date = newTransaction.date,
//                id = currentRecord.id
//        )
//
//
//        ResponseEntity.ok().body(transactionRepository.save(updatedRecord))
//    }.orElse(ResponseEntity.notFound().build())
//
//    fun deleteRecord(taskId: Long): ResponseEntity<Void> =
//            transactionRepository.findById(taskId).map { task ->
//                transactionRepository.delete(task)
//                ResponseEntity<Void>(HttpStatus.ACCEPTED)
//            }.orElse(ResponseEntity.notFound().build())


    fun saveAll(transactions: MutableList<TransactionCreationDto>, owner_id: String) {
        transactions.forEach {
            transactionRepository.save(Transaction(
                    owner = userRepository.findById(owner_id).get(),
                    subcategory = subcategoryRepository.findById(1).get(),
                    title = it.title,
                    description = it.description,
                    currency = currencyRepository.findById(756).get(),
                    amount = it.amount,
                    period = it.period,
                    date = it.date,
            ))
        }
    }


    fun findPaginated(pageNo: Int, pageSize: Int, sortField: String, sortDirection: Sort.Direction): Page<Transaction> {
        val sort = when (sortDirection) {
            Sort.Direction.ASC -> Sort.by(sortField).ascending()
            Sort.Direction.DESC -> Sort.by(sortField).descending()
        }

        val pageable: Pageable = PageRequest.of(pageNo - 1, pageSize, sort)
        return this.transactionRepository.findAll(pageable)
    }
}