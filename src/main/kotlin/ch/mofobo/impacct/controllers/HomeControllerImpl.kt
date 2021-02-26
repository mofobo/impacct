package ch.mofobo.impacct.controllers

import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class HomeControllerImpl : HomeController {

    companion object {
        private const val TEMPLATE_DIR = "home/"
        private const val TEMPLATE_HOME = TEMPLATE_DIR.plus("home")
    }

    override fun getHome(user: OidcUser?, model: Model): String {
        model.addAttribute("user", user)
        return TEMPLATE_HOME
    }
}