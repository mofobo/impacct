package ch.mofobo.impacct.services

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.TransactionDto
import ch.mofobo.impacct.entities.Category
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.repositories.CategoryRepository
import ch.mofobo.impacct.repositories.TransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TransactionService(
        private val transactionRepository: TransactionRepository,
        private val categoryRepository: CategoryRepository) {

    fun findPaginated(ownerEmail: String, pageNo: Int, pageSize: Int, sortField: String, sortDirection: Sort.Direction): Page<Transaction> {
        val sort = when (sortDirection) {
            Sort.Direction.ASC -> Sort.by(sortField).ascending()
            Sort.Direction.DESC -> Sort.by(sortField).descending()
        }

        val pageable: Pageable = PageRequest.of(pageNo - 1, pageSize, sort)
        return this.transactionRepository.findAllByOwnerEmail(ownerEmail, pageable)
    }

    fun save(dto: TransactionDto, ownerEmail: String) {
        val category = categoryRepository.getOne(dto.categoryId)

        System.out.println("TransactionService.category: $category")
        System.out.println("TransactionService.categoryId: ${dto.categoryId}")
        System.out.println("TransactionService.period: ${dto.period}")

        val trx = Transaction(
                ownerEmail = ownerEmail,
                type = dto.type.name,
                category = category,
                title = dto.title,
                description = dto.description,
                amount = dto.amount,
                period = dto.period.name,
                date = dto.date
        )

        System.out.println("Transaction.ownerEmail: ${trx.ownerEmail}")
        System.out.println("Transaction.type: ${trx.type}")
        System.out.println("Transaction.category: ${trx.category}")
        System.out.println("Transaction.title: ${trx.title}")
        System.out.println("Transaction.description: ${trx.description}")
        System.out.println("Transaction.amount: ${trx.amount}")
        System.out.println("Transaction.period: ${trx.period}")
        System.out.println("Transaction.date: ${trx.date}")

        transactionRepository.save(trx)
    }

    fun getCategories(): List<CategoryDto> {
        return categoryRepository.findAll().map {
            CategoryDto(it.id!!, it.name, it.description)
        }
    }
}