package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.TransactionDto
import org.springframework.data.domain.Sort
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RequestMapping(value = ["/transactions"])
interface TransactionController {

    companion object {
        const val GET_PAGINATED_TABLE_URL = "/page/{page}"
        const val GET_CREATE_FORM_URL = "/create"
        const val GET_EDIT_FORM_URL = "/{transactionId}/edit"
        const val SAVE_URL = "/save"
        const val UPDATE_URL = "/{transactionId}/update"
        const val DELETE_URL = "/{transactionId}/delete"
    }

    @GetMapping(GET_PAGINATED_TABLE_URL)
    fun getPaginatedTable(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable(value = "page")
            page: Int,
            @RequestParam("sortField")
            sortField: String?,
            @RequestParam("sortDir")
            sortDir: Sort.Direction?,
            model: Model,
            principal: Principal?): String

    @GetMapping(GET_CREATE_FORM_URL)
    fun getCreateForm(
            @AuthenticationPrincipal user: OidcUser,
            model: Model): String

    @GetMapping(GET_EDIT_FORM_URL)
    fun getEditForm(
            @PathVariable(value = "transactionId")
            transactionId: Int,
            @ModelAttribute transaction: CategoryDto,
            model: Model): String

    @PostMapping(SAVE_URL)
    fun save(
            @AuthenticationPrincipal
            user: OidcUser,
            @ModelAttribute
            transactionDto: TransactionDto,
            model: Model): String

    @PostMapping(UPDATE_URL)
    fun update(
            @AuthenticationPrincipal
            user: OidcUser,
            @PathVariable(value = "transactionId")
            transactionId: Int,
            @ModelAttribute
            transactionDto: TransactionDto,
            result: BindingResult,
            model: Model): String

    @GetMapping(DELETE_URL)
    fun delete(
            @PathVariable("transactionId")
            transactionId: Int,
            model: Model?): String
}