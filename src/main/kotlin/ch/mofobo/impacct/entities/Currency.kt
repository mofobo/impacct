package ch.mofobo.impacct.entities

import javax.persistence.*

@Entity(name = "currencies")
class Currency(
        @Id
        @GeneratedValue
        @Column(name = "currency_id")
        var id: Long? = null,

        var numeric_code: Long,

        var alphabetic_code: String,

        var decimal_digits: Long
)