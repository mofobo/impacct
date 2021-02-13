package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.TransactionCreationDto
import ch.mofobo.impacct.dtos.TransactionsCreationDto
import ch.mofobo.impacct.entities.Transaction
import ch.mofobo.impacct.enums.Period
import ch.mofobo.impacct.services.TransactionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping(value = ["/transactions"])
class TransactionController(val transactionService: TransactionService) {

    @GetMapping("/page/{pageNo}")
    fun findPaginated(
            @PathVariable(value = "pageNo")
            pageNo: Int,
            @RequestParam("sortField")
            sortField: String?,
            @RequestParam("sortDir")
            sortDir: Sort.Direction?,
            model: Model): String? {

        val field = sortField ?: "id"
        val direction = sortDir ?: Sort.Direction.ASC
        val size = 20

        val page: Page<Transaction> = transactionService.findPaginated(
                pageNo,
                size,
                field,
                direction
        )

        val reverseSortDir = if (direction.isAscending) Sort.Direction.DESC.name else Sort.Direction.DESC.name

        model.addAttribute("currentPage", pageNo)
        model.addAttribute("totalPages", page.totalPages)
        model.addAttribute("totalItems", page.totalElements)
        model.addAttribute("sortField", field)
        model.addAttribute("sortDir", direction.toString())
        model.addAttribute("reverseSortDir", reverseSortDir)
        model.addAttribute("transactions", page.content)
        return "transactions/table"
    }

    @GetMapping("/create")
    fun showCreateForm(@AuthenticationPrincipal user: OidcUser, model: Model): String? {
        val transactionsCreationDto = TransactionsCreationDto()
        for (i in 1..3) {
            transactionsCreationDto.transactionsDto.add(
                    TransactionCreationDto(
                            subcategory = 0,
                            title = "",
                            description = "",
                            amount = 0,
                            period = Period.JANUARY,
                            date = ""
                    )
            )
        }
        model.addAttribute("form", transactionsCreationDto)
        return "transactions/create-form"
    }

    @PostMapping("/save")
    fun saveRecords(@AuthenticationPrincipal user: OidcUser, @ModelAttribute form: TransactionsCreationDto, model: Model): String? {
        transactionService.saveAll(form.transactionsDto,user.idToken.tokenValue)
        model.addAttribute("records", transactionService.getRecords())
        return "redirect:/transactions/page/1"
    }
}