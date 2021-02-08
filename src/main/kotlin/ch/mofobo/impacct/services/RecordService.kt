package ch.mofobo.impacct.services

import ch.mofobo.impacct.entities.Record
import ch.mofobo.impacct.repositories.RecordRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class RecordService(private val recordRepository: RecordRepository) {

    fun getRecords(): MutableList<Record> = recordRepository.findAll()

    fun addRecord(task: Record): ResponseEntity<Record> =
            ResponseEntity.ok(recordRepository.save(task))

    fun getRecordById(taskId: Long): ResponseEntity<Record> =
            recordRepository.findById(taskId).map { task ->
                ResponseEntity.ok(task)
            }.orElse(ResponseEntity.notFound().build())

    fun putRecord(taskId: Long, newRecord: Record): ResponseEntity<Record> = recordRepository.findById(taskId).map { currentRecord ->
        val updatedRecord = Record(
                title = newRecord.title,
                description = newRecord.description,
                value = newRecord.value,
                date = newRecord.date,
                id = currentRecord.id
        )


        ResponseEntity.ok().body(recordRepository.save(updatedRecord))
    }.orElse(ResponseEntity.notFound().build())

    fun deleteRecord(taskId: Long): ResponseEntity<Void> =
            recordRepository.findById(taskId).map { task ->
                recordRepository.delete(task)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())

    fun saveAll(records: MutableList<Record>) {
        recordRepository.saveAll(records)
    }


    fun findPaginated(pageNo: Int, pageSize: Int, sortField: String, sortDirection: Sort.Direction): Page<Record> {
        val sort = when (sortDirection) {
            Sort.Direction.ASC -> Sort.by(sortField).ascending()
            Sort.Direction.DESC -> Sort.by(sortField).descending()
        }

        val pageable: Pageable = PageRequest.of(pageNo - 1, pageSize, sort)
        return this.recordRepository.findAll(pageable)
    }
}