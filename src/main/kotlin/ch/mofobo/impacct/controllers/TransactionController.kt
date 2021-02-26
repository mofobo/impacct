package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.dtos.TransactionDto
import org.springframework.data.domain.Sort
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@RequestMapping(value = ["/transactions"])
interface TransactionController {

    @GetMapping("/page/{pageNo}")
    fun getPaginatedTable(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable(value = "pageNo")
            pageNo: Int,
            @RequestParam("sortField")
            sortField: String?,
            @RequestParam("sortDir")
            sortDir: Sort.Direction?,
            model: Model): String

    @GetMapping("/create")
    fun getCreateForm(
            @AuthenticationPrincipal user: OidcUser,
            model: Model): String

    @GetMapping("/{transactionId}/edit")
    fun getEditForm(
            @PathVariable(value = "transactionId")
            transactionId: Int,
            @ModelAttribute transaction: CategoryDto,
            model: Model): String

    @PostMapping("/save")
    fun save(
            @AuthenticationPrincipal
            user: OidcUser,
            @ModelAttribute
            transactionDto: TransactionDto,
            model: Model): String

    @PostMapping("/{transactionId}/update")
    fun update(
            @AuthenticationPrincipal
            user: OidcUser,
            @PathVariable(value = "transactionId")
            transactionId: Int,
            @ModelAttribute
            transactionDto: TransactionDto,
            result: BindingResult,
            model: Model): String

    @GetMapping("/{transactionId}/delete")
    fun delete(
            @PathVariable("transactionId")
            transactionId: Int,
            model: Model?): String
}