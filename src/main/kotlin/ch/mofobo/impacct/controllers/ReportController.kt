package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.CategoryDto
import org.springframework.data.domain.Sort
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RequestMapping("/reports")
interface ReportController {

    @GetMapping("/year/{year}")
    fun getYearlyReport(
            @PathVariable(value = "year")
            year: Int,
            model: Model): String
}