package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.RecordsCreationDto
import ch.mofobo.impacct.entities.Record
import ch.mofobo.impacct.services.RecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
class RecordsController {

    @Autowired
    val recordService: RecordService? = null

    @RequestMapping("/securedPage")
    fun securedPage(model: Model?, principal: Principal?): String {
        return "securedPage"
    }

    @RequestMapping("/")
    fun index(model: Model?, principal: Principal?): String {
        return "index"
    }

    @GetMapping("/records/all")
    fun showAll(model: Model): String? {
        model.addAttribute("records", recordService?.getRecords())
        return "records/allRecordsTable"
    }

    @GetMapping("/records/create")
    fun showCreateForm(model: Model): String? {
        val recordsForm = RecordsCreationDto()
        for (i in 1..3) {
            recordsForm.records.add(Record(
                    title = "",
                    description = "",
                    value = 0,
                    date = ""))
        }
        model.addAttribute("form", recordsForm)
        return "records/creationForm"
    }

    @PostMapping("/records/save")
    fun saveRecords(@ModelAttribute form: RecordsCreationDto, model: Model): String? {
        recordService?.saveAll(form.records)
        model.addAttribute("records", recordService?.getRecords())
        return "redirect:/records/all"
    }
}