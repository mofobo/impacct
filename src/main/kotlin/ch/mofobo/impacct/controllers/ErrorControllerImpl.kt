package ch.mofobo.impacct.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class ErrorControllerImpl : ErrorController {

    companion object {
        private const val TEMPLATE_DIR = "errors/"
        private const val TEMPLATE_403 = TEMPLATE_DIR.plus("error_403")
    }

    override fun error403(model: Model): String {
        return TEMPLATE_403
    }
}