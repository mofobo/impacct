package ch.mofobo.impacct.services

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.PieChartData
import ch.mofobo.impacct.dtos.StackedGroupedChartData
import ch.mofobo.impacct.dtos.TransactionDto
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.enums.TransactionType
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
            val transactionsByCategory = transactionRepository.findAllByCategoryAndDateBetweenAndType(category, YearMonth.of(year, 1), YearMonth.of(year + 1, 1), TransactionType.EXPENSE.name)
            val label = category.name
            val value = transactionsByCategory.sumBy { it.amount }.toFloat() / 100.0f
            if (value > 0) pieChartDataList.add(PieChartData(label, value))
        }
        return pieChartDataList
    }

    fun getStackedGroupedChartData(year: Int): MutableList<StackedGroupedChartData> {

        val result = mutableListOf<StackedGroupedChartData>()

        val expensesByMonth = HashMap<Int, Float>()
        for (month in 1..12) {
            val expenses = transactionRepository.findAllByTypeAndDate(TransactionType.EXPENSE.name, YearMonth.of(year, month))
            val totalExpenses = expenses.sumBy { it.amount }.toFloat() / 100.0f
            expensesByMonth.put(month-1, totalExpenses)
        }
        result.add(StackedGroupedChartData(TransactionType.EXPENSE.name, expensesByMonth.values.toTypedArray(), TransactionType.EXPENSE.name))

        val map = HashMap<String, HashMap<Int, Float>>()

        for (month in 1..12) {
            val incomes = transactionRepository.findAllByTypeAndDate(TransactionType.INCOME.name, YearMonth.of(year, month))
            val incomesByCategory = incomes.groupBy { it.category.name }

            incomesByCategory.keys.forEach { category ->
                if (map.containsKey(category).not()) map.put(category, HashMap())
                map.get(category)!!.put(month, incomesByCategory.get(category)!!.sumBy { it.amount }.toFloat() / 100.0f)
            }
        }

        map.forEach {
            result.add(StackedGroupedChartData(it.key, it.value.values.toTypedArray(), TransactionType.INCOME.name))
        }
        return result
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