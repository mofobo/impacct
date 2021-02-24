package ch.mofobo.impacct.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeControllerImpl : HomeController {

    companion object {
        private const val TEMPLATE_DIR = "home/"
        private const val TEMPLATE_HOME = TEMPLATE_DIR.plus("home")
    }

    override fun getHome(model: Model): String {
        model.addAttribute("pass", 50)
        model.addAttribute("fail", 50)
        return TEMPLATE_HOME
    }
}