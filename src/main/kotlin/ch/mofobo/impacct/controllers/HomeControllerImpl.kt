package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.PieChartData
import ch.mofobo.impacct.services.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeControllerImpl(val transactionService: TransactionService) : HomeController {

    companion object {
        private const val TEMPLATE_DIR = "home/"
        private const val TEMPLATE_HOME = TEMPLATE_DIR.plus("home")
    }

    override fun getHome(model: Model): String {
        val pieChartDataList = transactionService.getPieChartData()
        model.addAttribute("list", pieChartDataList)
        return TEMPLATE_HOME
    }
}