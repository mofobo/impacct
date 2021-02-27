package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.services.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class ReportControllerImpl(val transactionService: TransactionService) : ReportController {

    companion object {
        private const val TEMPLATE_DIR = "reports/"
        private const val TEMPLATE_PIE_CHART = TEMPLATE_DIR.plus("reports")
    }

    override fun getYearlyReport(selectedYear: Int, model: Model): String {
        val dates = transactionService.getAllDates()
        val availableYears = dates?.map { it.year.toString() }?.distinct()?.sortedByDescending { it }?.toMutableList()
        availableYears?.add(0,"Select year")

        val pieChartDataList = transactionService.getPieChartData(selectedYear)

        model.addAttribute("availableYears", availableYears)
        model.addAttribute("selectedYear", selectedYear)
        model.addAttribute("list", pieChartDataList)
        return TEMPLATE_PIE_CHART
    }
}