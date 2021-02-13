package ch.mofobo.impacct.entities

import ch.mofobo.impacct.enums.Period
import javax.persistence.*

@Entity(name = "transactions")
class Transaction(
        @Id
        @GeneratedValue
        @Column(name = "transaction_id")
        var id: Long? = null,

        @OneToOne
        @JoinColumn(table = "users", name = "user_id")
        @Column(name = "owner_id")
        var owner: User,

        @OneToOne
        @JoinColumn(name = "subcategory_id")
        var subcategory: Subcategory?,

        var title: String,

        var description: String,

        @OneToOne
        @JoinColumn(name = "currency_id")
        var currency: Currency,
        var amount: Long,

        @Column(name = "fiscal_period")
        var period: Period,

        var date: String
)