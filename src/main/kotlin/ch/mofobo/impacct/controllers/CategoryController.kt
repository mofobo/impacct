package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import org.springframework.data.domain.Sort
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RequestMapping("/categories")
interface CategoryController {

    companion object {
        const val GET_PAGINATED_TABLE_URL = "/page/{page}"
        const val GET_CREATE_FORM_URL = "/create"
        const val GET_EDIT_FORM_URL = "/{categoryId}/edit"
        const val SAVE_URL = "/save"
        const val UPDATE_URL = "/{categoryId}/update"
        const val DELETE_URL = "/{categoryId}/delete"
    }

    @GetMapping(GET_PAGINATED_TABLE_URL)
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

    @GetMapping(GET_CREATE_FORM_URL)
    fun getCreateForm(
            @AuthenticationPrincipal user: OidcUser,
            model: Model): String

    @GetMapping(GET_EDIT_FORM_URL)
    fun getEditForm(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable(value = "categoryId")
            categoryId: Int,
            @ModelAttribute categoryDto: CategoryDto,
            model: Model): String

    @PostMapping(SAVE_URL)
    fun save(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @ModelAttribute categoryDto: CategoryDto,
            model: Model): String

    @PostMapping(UPDATE_URL)
    fun update(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable(value = "categoryId")
            categoryId: Int,
            @ModelAttribute categoryDto: CategoryDto,
            result: BindingResult,
            model: Model): String

    @GetMapping(DELETE_URL)
    fun delete(
            @AuthenticationPrincipal
            oidcUser: OidcUser,
            @PathVariable("categoryId") categoryId: Int,
            model: Model?): String
}