package ch.mofobo.impacct.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "categories")
class Category(
        @Id
        @GeneratedValue
        @Column(name = "category_id")
        var id: Long? = null,

        var name: String,

        var description: String
)