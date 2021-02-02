package ch.mofobo.impacct.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0
    private val title: String? = null
    private val description: String? = null
}