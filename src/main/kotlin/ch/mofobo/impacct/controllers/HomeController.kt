package ch.mofobo.impacct.controllers

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
interface HomeController {

    @GetMapping()
    fun getHome(
            @AuthenticationPrincipal
            user: OidcUser?,
            model: Model
    ): String

    @GetMapping("/403")
    fun error403(): String
}