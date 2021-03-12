package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.TransactionDto
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.enums.TransactionType
import ch.mofobo.impacct.services.TransactionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import java.security.Principal
import java.time.YearMonth


@Controller
class TransactionControllerImpl(val transactionService: TransactionService) : TransactionController {

    companion object {
        private const val TEMPLATE_DIR = "transactions/"
        private const val TEMPLATE_TABLE = TEMPLATE_DIR.plus("table")
        private const val TEMPLATE_CREATE = TEMPLATE_DIR.plus("create-form")
        private const val TEMPLATE_EDIT = TEMPLATE_DIR.plus("edit-form")

        private const val REDIRECT_ROOT = "redirect:/transactions"
        private const val REDIRECT_TABLE = REDIRECT_ROOT.plus("/page/1")
    }

    override fun getPaginatedTable(oidcUser: OidcUser, page: Int, sortField: String?, sortDir: Sort.Direction?, model: Model, principal: Principal?): String {
        val field = sortField ?: "id"
        val direction = sortDir ?: Sort.Direction.ASC
        val size = 20

        val page: Page<Transaction> = transactionService.findPaginated(
                oidcUser.email,
                page,
                size,
                field,
                direction
        )

        val reverseSortDir = if (direction.isAscending) Sort.Direction.DESC.name else Sort.Direction.ASC.name

        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", page.totalPages)
        model.addAttribute("totalItems", page.totalElements)
        model.addAttribute("sortField", field)
        model.addAttribute("sortDir", direction.toString())
        model.addAttribute("reverseSortDir", reverseSortDir)
        model.addAttribute("transactions", page.content)
        return TEMPLATE_TABLE
    }

    override fun getCreateForm(user: OidcUser, model: Model): String {
        val transaction = TransactionDto(
                type = TransactionType.EXPENSE,
                categoryId = 0,
                title = "",
                description = "",
                amount = 0,
                date = YearMonth.now()
        )

        val categories = transactionService.getCategories()

        model.addAttribute("transaction", transaction)
        model.addAttribute("categories", categories)
        return TEMPLATE_CREATE
    }

    override fun getEditForm(transactionId: Int, transaction: CategoryDto, model: Model): String {
        val transaction = transactionService.get(transactionId)

        val categories = transactionService.getCategories()
        model.addAttribute("categories", categories)
        model.addAttribute("transaction", TransactionDto(transaction))
        return TEMPLATE_EDIT
    }

    override fun save(user: OidcUser, transactionDto: TransactionDto, model: Model): String {
        transactionService.save(transactionDto, user.email)
        return REDIRECT_TABLE
    }

    override fun update(user: OidcUser, transactionId: Int, transactionDto: TransactionDto, result: BindingResult, model: Model): String {
        System.out.println("transactionDto.id: " + transactionId)
        transactionDto.id = transactionId
        transactionService.save(transactionDto, user.email)
        return REDIRECT_TABLE
    }

    override fun delete(transactionId: Int, model: Model?): String {
        transactionService.delete(transactionId)
        return REDIRECT_TABLE
    }
}