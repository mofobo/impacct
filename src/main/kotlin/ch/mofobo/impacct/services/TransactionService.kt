package ch.mofobo.impacct.services

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.PieChartData
import ch.mofobo.impacct.dtos.TransactionDto
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.repositories.CategoryRepository
import ch.mofobo.impacct.repositories.TransactionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.YearMonth

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
        val category = categoryRepository.getOne(dto.categoryId!!)

        val trx = Transaction(
                id = dto.id,
                ownerEmail = ownerEmail,
                type = dto.type!!.name,
                category = category,
                title = dto.title!!,
                description = dto.description,
                amount = dto.amount!!,
                date = dto.date!!
        )

        System.out.println("Transaction.ownerEmail: ${trx.ownerEmail}")
        System.out.println("Transaction.type: ${trx.type}")
        System.out.println("Transaction.category: ${trx.category}")
        System.out.println("Transaction.title: ${trx.title}")
        System.out.println("Transaction.description: ${trx.description}")
        System.out.println("Transaction.amount: ${trx.amount}")
        System.out.println("Transaction.date: ${trx.date.year}")

        transactionRepository.save(trx)
    }

    fun getCategories(): List<CategoryDto> {
        return categoryRepository.findAll().map {
            CategoryDto(it.id!!, it.name, it.description)
        }
    }

    fun getPieChartData(year: Int): MutableList<PieChartData> {
        val pieChartDataList = mutableListOf<PieChartData>()
        val categories = categoryRepository.findAll()
        categories.forEach { category ->
            val transactionsByCategory = transactionRepository.findAllByCategoryAndDateBetween(category, YearMonth.of(year, 1), YearMonth.of(year + 1, 1))
            val label = category.name
            val value = transactionsByCategory.sumBy { it.amount }
            if (value > 0) pieChartDataList.add(PieChartData(label, value))
        }
        return pieChartDataList
    }

    fun delete(transactionId: Int) {
        transactionRepository.deleteById(transactionId)
    }

    fun get(transactionId: Int): Transaction {
        return transactionRepository.getOne(transactionId)
    }

    fun getAllDates(): MutableList<YearMonth>? {
        return transactionRepository.getAllDates()
    }
}