package ch.mofobo.impacct.entities

import javax.persistence.*

@Entity(name = "subcategories")
class Subcategory(
        @Id
        @GeneratedValue
        @Column(name = "subcategory_id")
        var id: Long? = null,

        @OneToOne
        @JoinColumn(name = "category_id")
        var category: Category,

        var name: String,

        var description: String
)
