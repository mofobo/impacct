package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import ch.mofobo.impacct.entities.Category
import ch.mofobo.impacct.services.CategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult


@Controller
class CategoryControllerImpl(val categoryService: CategoryService) : CategoryController {

    companion object {
        private const val TEMPLATE_DIR = "categories/"
        private const val TEMPLATE_TABLE = TEMPLATE_DIR.plus("table")
        private const val TEMPLATE_CREATE = TEMPLATE_DIR.plus("create-form")
        private const val TEMPLATE_EDIT = TEMPLATE_DIR.plus("edit-form")

        private const val REDIRECT_ROOT = "redirect:/categories"
        private const val REDIRECT_TABLE = REDIRECT_ROOT.plus("/page/1")
    }

    override fun getPaginatedTable(oidcUser: OidcUser, pageNo: Int, sortField: String?, sortDir: Sort.Direction?, model: Model): String {
        val field = sortField ?: "id"
        val direction = sortDir ?: Sort.Direction.DESC
        val size = 20

        val page: Page<Category> = categoryService.findPaginated(pageNo, size, field, direction)
        val reverseSortDir = if (direction.isAscending) Sort.Direction.DESC.name else Sort.Direction.ASC.name

        model.addAttribute("currentPage", pageNo)
        model.addAttribute("totalPages", page.totalPages)
        model.addAttribute("totalItems", page.totalElements)
        model.addAttribute("sortField", field)
        model.addAttribute("sortDir", direction.toString())
        model.addAttribute("reverseSortDir", reverseSortDir)
        model.addAttribute("categories", page.content)
        return TEMPLATE_TABLE
    }

    override fun getCreateForm(user: OidcUser, model: Model): String {
        model.addAttribute("category", CategoryDto())
        return TEMPLATE_CREATE
    }

    override fun getEditForm(categoryId: Int, categoryDto: CategoryDto, model: Model): String {
        val category = categoryService.get(categoryId)
        model.addAttribute("category", CategoryDto(category))
        return TEMPLATE_EDIT
    }

    override fun save(categoryDto: CategoryDto, model: Model): String {
        categoryService.save(categoryDto)
        return REDIRECT_TABLE
    }

    override fun update(categoryId: Int, categoryDto: CategoryDto, result: BindingResult, model: Model): String {
        categoryService.save(CategoryDto(categoryId, categoryDto.name!!, categoryDto.description!!))
        return REDIRECT_TABLE
    }

    override fun delete(categoryId: Int, model: Model?): String {
        categoryService.delete(categoryId)
        return REDIRECT_TABLE
    }
}