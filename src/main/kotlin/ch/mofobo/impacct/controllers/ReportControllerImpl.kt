package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.services.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class ReportControllerImpl(val transactionService: TransactionService) : ReportController {

    companion object {
        private const val TEMPLATE_DIR = "reports/"
        private const val TEMPLATE_PIE_CHART = TEMPLATE_DIR.plus("pie-chart")
    }

    override fun getYearlyReport(year: Int, model: Model): String {
        val dates = transactionService.getAllDates()
        val years = dates?.map { it.year.toString() }?.distinct()?.sortedByDescending { it }?.toMutableList()
        years?.add(0,"Select year")

        val pieChartDataList = transactionService.getPieChartData(year)

        model.addAttribute("dates", years)
        model.addAttribute("year", year)
        model.addAttribute("list", pieChartDataList)
        return TEMPLATE_PIE_CHART
    }
}