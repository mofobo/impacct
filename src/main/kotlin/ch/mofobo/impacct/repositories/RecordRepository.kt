package ch.mofobo.impacct.repositories

import ch.mofobo.impacct.entities.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository : JpaRepository<Record, Long>