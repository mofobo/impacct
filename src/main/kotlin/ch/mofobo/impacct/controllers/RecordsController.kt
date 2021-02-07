package ch.mofobo.impacct.controllers

import ch.mofobo.impacct.dtos.RecordsCreationDto
import ch.mofobo.impacct.entities.Record
import ch.mofobo.impacct.services.RecordService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.Principal


@Controller
class RecordsController(val recordService: RecordService) {

    @RequestMapping("/securedPage")
    fun securedPage(model: Model?, principal: Principal?): String {
        return "securedPage"
    }

    @RequestMapping("/")
    fun index(model: Model?, principal: Principal?): String {
        return "index"
    }


    @GetMapping("/records/page/{pageNo}")
    fun findPaginated(
            @PathVariable(value = "pageNo")
            pageNo: Int,
            @RequestParam("sortField")
            sortField: String?,
            @RequestParam("sortDir")
            sortDir: Sort.Direction?,
            model: Model): String? {

        val field = sortField ?: "id"
        val direction = sortDir ?: Sort.Direction.ASC
        val size = 20

        val page: Page<Record> = recordService.findPaginated(
                pageNo,
                size,
                field,
                direction
        )

        val reverseSortDir = if (direction.isAscending) Sort.Direction.DESC.name else Sort.Direction.DESC.name

        model.addAttribute("currentPage", pageNo)
        model.addAttribute("totalPages", page.totalPages)
        model.addAttribute("totalItems", page.totalElements)
        model.addAttribute("sortField", field)
        model.addAttribute("sortDir", direction.toString())
        model.addAttribute("reverseSortDir", reverseSortDir)
        model.addAttribute("listRecords", page.content)
        return "records/table"
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
        recordService.saveAll(form.records)
        model.addAttribute("records", recordService.getRecords())
        return "redirect:/records/all"
    }
}