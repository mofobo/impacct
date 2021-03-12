package ch.mofobo.impacct.controllers

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/error")
interface ErrorController {

    @GetMapping("/403")
    fun error403(model: Model): String
}