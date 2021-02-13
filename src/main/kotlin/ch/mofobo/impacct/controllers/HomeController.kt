package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.entities.User
import ch.mofobo.impacct.services.TransactionService
import ch.mofobo.impacct.services.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.security.Principal


@Controller
@RequestMapping(value = ["/home"])
class HomeController(val transactionService: TransactionService, val userService: UserService) {

    @GetMapping
    fun index(@AuthenticationPrincipal oidcUser: OidcUser, model: Model?, principal: Principal?): ModelAndView {
        val token: String = oidcUser.idToken.tokenValue
        val user = userService.get(token) ?: userService.create(User(id = token, name = oidcUser.name))
        //  val isAdmin: Boolean = oidcUser.userInfo.getClaimAsStringList("groups").contains("Admin")
        val mav = ModelAndView()
        mav.addObject("user", user.name)
        mav.viewName = "index"
        return mav
    }
}