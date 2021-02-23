package ch.mofobo.impacct.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = ["/"])
class HomeController {
    @GetMapping
    fun home(): String {
        return "redirect:/transactions/page/1"
    }

}