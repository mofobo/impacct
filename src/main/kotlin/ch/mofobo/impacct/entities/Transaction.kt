package ch.mofobo.impacct.entities

import ch.mofobo.impacct.enums.Period
import ch.mofobo.impacct.enums.TransactionType
import javax.persistence.*

@Entity(name = "transactions")
class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "owner_email")
        var ownerEmail: String,

        @Column(name = "type", columnDefinition = "TransactionType")
        var type: String,

        @OneToOne
        @JoinColumn(name = "category_id")
        var category: Category,

        var title: String,

        var description: String? = null,

        var amount: Int,

        @Column(name = "period")
        var period: String,

        var date: String
)