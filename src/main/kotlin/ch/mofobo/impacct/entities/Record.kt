package ch.mofobo.impacct.entities

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "records")
class Record(
        var title: String,
        var description: String,
        var value: Long,
        var date: String,
        @Id @GeneratedValue var id: Long? = null
)