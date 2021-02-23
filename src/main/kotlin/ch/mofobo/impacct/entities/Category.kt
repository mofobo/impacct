package ch.mofobo.impacct.entities

import javax.persistence.*

@Entity(name = "categories")
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        var name: String,

        var description: String
)