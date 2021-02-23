package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import org.springframework.data.domain.Sort
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RequestMapping("/categories")
interface CategoryController {

    @GetMapping("/page/{page}")
    fun getPaginatedTable(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable(value = "page")
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

    @GetMapping("/{categoryId}/edit")
    fun getEditForm(
            @PathVariable(value = "categoryId")
            categoryId: Int,
            @ModelAttribute categoryDto: CategoryDto,
            model: Model): String

    @PostMapping("/save")
    fun save(
            @ModelAttribute categoryDto: CategoryDto,
            model: Model): String

    @PostMapping("/{categoryId}/update")
    fun update(
            @PathVariable(value = "categoryId")
            categoryId: Int,
            @ModelAttribute categoryDto: CategoryDto,
            result: BindingResult,
            model: Model): String

    @PostMapping("/{categoryId}/delete")
    fun delete(
            @PathVariable("categoryId") categoryId: Int,
            model: Model?): String
}